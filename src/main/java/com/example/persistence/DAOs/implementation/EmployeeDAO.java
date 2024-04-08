package com.example.persistence.DAOs.implementation;


import com.example.persistence.DAOs.interfaces.EmployeeDAOInt;
import com.example.persistence.entities.Department;
import com.example.persistence.entities.Employee;
import com.example.persistence.entities.Salary;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class EmployeeDAO implements EmployeeDAOInt {



    @Override
    public boolean save(Employee employee, EntityManager em)  {

        System.out.println("----------------------------------------------------------------");
        System.out.println(employee.getSalary().toString());
        System.out.println("----------------------------------------------------------------");
        System.out.println(employee.toString() );
        System.out.println("----------------------------------------------------------------");
        DepartmentDAO departmentDAO = new DepartmentDAO();
        Department department = departmentDAO.getDepartmentByName(employee.getDepartment().getDepartmentName(), em);

        if(department == null){
            return false;

        }

        em.getTransaction().begin();
        employee.setDepartment(department);
        Salary salary = new Salary();
        salary.setBasicSalary(employee.getSalary().getBasicSalary());
        employee.setSalary(salary);
        salary.setEmployee(employee);
        em.persist(employee);
        em.persist(salary);
        Employee employee1 = em.find(Employee.class, employee.getEmployeeId());
        em.getTransaction().commit();
        em.close();

        return !Objects.isNull(employee1);
    }

    @Override
    public Optional<Employee> get(Integer id, EntityManager em) {
        return Optional.ofNullable(em.find(Employee.class, id));
    }

    @Override
    public boolean update(Employee employee, EntityManager em) {
        try {
            em.getTransaction().begin();
//                Employee managedEmployee = em.find(Employee.class, employee.getEmployeeId());
//                if (managedEmployee == null) {
//                    throw new NoResultException("Employee not found and Cant Update");
//                }
//

            Employee updatedEmployee = em.merge(employee);
            em.flush();
            em.getTransaction().commit();
            em.close();
                System.out.println("Employee Updated");
                return true;
        }catch (Exception e) {
            e.printStackTrace();
            throw new NoResultException("Cant Update");
        }

    }

    @Override
    public void delete(Employee employee, EntityManager em) {
        em.remove(employee);
    }

    @Override
    public Optional<Employee> delete(int id, EntityManager em) {

        em.getTransaction().begin();
        Employee employee = em.find(Employee.class, id);
        if (Objects.isNull(employee)) {
            return Optional.empty();
        }
        em.remove(employee);
        em.getTransaction().commit();
        return Optional.of(employee);



    }

    @Override
    public List<Employee> getAllEmployees(EntityManager em) {

        return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }


}
