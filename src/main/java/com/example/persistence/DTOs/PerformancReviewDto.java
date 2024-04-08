package com.example.persistence.DTOs;

import com.example.persistence.entities.Employee;
import com.example.persistence.enums.PerformanceReating;
import lombok.Value;

import java.io.Serializable;
import java.sql.Date;

@Value
public class PerformancReviewDto  implements Serializable {

    private Integer reviewId;
    private String employeeName;
    private Integer employeeId;
    private Date reviewDate;
    private PerformanceReating performanceRating;
}
