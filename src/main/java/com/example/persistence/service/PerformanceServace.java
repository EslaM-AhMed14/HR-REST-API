package com.example.persistence.service;

import com.example.persistence.dao.impl.PerformanceReviewDAO;
import com.example.persistence.dto.EmployeeDto;
import com.example.persistence.dto.PerformancReviewDto;
import com.example.persistence.connect.Database;
import com.example.persistence.entity.Employee;
import com.example.persistence.entity.PerformanceReview;
import com.example.persistence.mapper.EmployeeMapper;
import com.example.persistence.mapper.PerformanceReviewMapper;

import java.util.List;

public class PerformanceServace {
    public static List<PerformancReviewDto> getAllPerformanceReview() {
        return Database.doInTransaction(em -> {
            PerformanceReviewDAO performanceDAO = new PerformanceReviewDAO();
            List<PerformanceReview>  Reviews = performanceDAO.getAllPerformanceReview(em);
            return PerformanceReviewMapper.INSTANCE.toPerformanceReviewDtoList(Reviews);
        });
    }

    public static void  addPerformanceReview(PerformancReviewDto performanceReviewDto) {
         Database.doInTransactionWithoutResult(em -> {
            PerformanceReviewDAO performanceDAO = new PerformanceReviewDAO();
             System.out.println("performance review dto" + performanceReviewDto.toString());
           PerformanceReview performanceReview = PerformanceReviewMapper.INSTANCE
                    .performanceReviewDtoToPerformanceReview(performanceReviewDto);
             System.out.println("preformance"+performanceReview.toString());
           EmployeeDto employee = EmployeeService.getEmployeeById(performanceReviewDto.getReviwerId());
           if(employee == null) {
            throw  new RuntimeException("the employee does not exist in the database");
           }
             System.out.println("employee"+employee.toString());
            Employee employee1 = EmployeeMapper.INSTANCE.employeeDtoToEmployee(employee);
                performanceReview.setEmployee(employee1);
             performanceDAO.addPerformanceReview(performanceReview, em);
        });
    }

    public static List<PerformancReviewDto> getEmployeesByPage(int pageId) {
        return Database.doInTransaction(em -> {
            PerformanceReviewDAO performanceDAO = new PerformanceReviewDAO();
            List<PerformanceReview> performanceReviews = performanceDAO.getEmployeesByPage(pageId, em);
            return PerformanceReviewMapper.INSTANCE.toPerformanceReviewDtoList(performanceReviews);
        });
    }
}
