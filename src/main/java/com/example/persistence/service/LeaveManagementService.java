package com.example.persistence.service;

import com.example.exception.ResourceNotFound;
import com.example.persistence.dao.impl.DepartmentDAO;
import com.example.persistence.dao.impl.EmployeeDAO;
import com.example.persistence.dao.impl.LeaveManagementDAO;
import com.example.persistence.dto.LeaveManagementDto;
import com.example.persistence.connect.Database;
import com.example.persistence.entity.Employee;
import com.example.persistence.entity.LeaveManagement;
import com.example.persistence.enums.LeaveStatus;
import com.example.persistence.mapper.LeaveManagementMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class LeaveManagementService {
    public static List<LeaveManagementDto> getAllLeaves() {
        return Database.doInTransaction(em->{
            LeaveManagementDAO leaveDAO = new LeaveManagementDAO();
           List <LeaveManagement> leaves =  leaveDAO.getAllPerformanceReview(em);

            return LeaveManagementMapper.INSTANCE.toLeaveManagementDtoList(leaves);
        });
    }

    public static void createLeave(LeaveManagementDto performanceReviewDto) {
        Database.doInTransactionWithoutResult(em -> {
            System.out.println("service Leave");
            EntityManagerFactory emf =
                    Persistence.createEntityManagerFactory("DB");
            EntityManager em1 = emf.createEntityManager();
            EmployeeDAO employeeDAO = new EmployeeDAO();
            Employee employee = employeeDAO.get(performanceReviewDto.getEmployeeId(), em1).orElse(null);
            if (employee == null) {
                throw new RuntimeException("Employee not found");
            }
            System.out.println("Employee found");
            em1.close();
            LeaveManagementDAO leaveDAO = new LeaveManagementDAO();
            LeaveManagement leave = LeaveManagementMapper.INSTANCE.leaveManagementDtoToLeaveManagement(performanceReviewDto);
            leave.setEmployee(employee);

            leave.setStatus(LeaveStatus.Pending);
            System.out.println("Leave set to pending");

            leaveDAO.save(leave, em);
            System.out.println("Leave created");
        });
    }


    public static void approveLeave(Integer leaveId, Integer managerId) {
        Database.doInTransactionWithoutResult(em -> {
            LeaveManagementDAO leaveDAO = new LeaveManagementDAO();
            LeaveManagement leave = leaveDAO.get(leaveId, em).orElse(null);
            if (leave == null) {
                throw new ResourceNotFound("Leave not found");
            }
            if (leave.getStatus() != LeaveStatus.Pending) {
                throw new RuntimeException("Leave is not pending, leave is already "+ leave.getStatus() +" From " + leave.getAccreditationDate());
            }
            DepartmentDAO departmentDAO = new DepartmentDAO();

            List<Employee> managers = departmentDAO.getAllManager(em);

            Employee manager = managers.stream().filter(m -> m.getEmployeeId().equals(managerId)).findFirst().orElse(null);

            if (manager == null) {
                throw new IllegalArgumentException("You Are not the manager, the Head Only can Reject the leave or the Direct Manager");
            }


            if( !manager.getDepartment().equals(leave.getEmployee().getDepartment())){
                if(!manager.getDepartment().getIsHead())
                    throw new IllegalArgumentException("You Are Not Direct Manager ,the Head Only can Acesept the leave and the Direct Manager");
            }

            leave.setStatus(LeaveStatus.Approved);

            LocalDate currentDate = LocalDate.now();
            Date sqlDate = Date.valueOf(currentDate);
            leave.setAccreditationDate(sqlDate);

            leaveDAO.save(leave, em);
        });
    }

    public static void rejectLeave(Integer leaveId, Integer managerId) {
        Database.doInTransactionWithoutResult(em -> {
            LeaveManagementDAO leaveDAO = new LeaveManagementDAO();
            LeaveManagement leave = leaveDAO.get(leaveId, em).orElse(null);
            if (leave == null) {
                throw new ResourceNotFound("Leave not found");
            }
            if (leave.getStatus() != LeaveStatus.Pending) {
                throw new RuntimeException("Leave is not pending, leave is already "+ leave.getStatus() +" From " + leave.getAccreditationDate());
            }
            DepartmentDAO departmentDAO = new DepartmentDAO();

            List<Employee> managers = departmentDAO.getAllManager(em);

            Employee manager = managers.stream().filter(m -> m.getEmployeeId().equals(managerId)).findFirst().orElse(null);

            if (manager == null) {
                throw new IllegalArgumentException("You Are not the manager, the Head Only can Reject the leave or the Direct Manager");
            }


            if( !manager.getDepartment().equals(leave.getEmployee().getDepartment())){
                if(!manager.getDepartment().getIsHead())
                    throw new IllegalArgumentException("You Are Not Direct Manager or The Head ,the Head Only can Reject the leave and the Direct Manager");
            }

            leave.setStatus(LeaveStatus.Rejected);

            LocalDate currentDate = LocalDate.now();
            Date sqlDate = Date.valueOf(currentDate);
            leave.setAccreditationDate(sqlDate);

            leaveDAO.save(leave, em);
        });
    }
}
