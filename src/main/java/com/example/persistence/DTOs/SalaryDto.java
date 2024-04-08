package com.example.persistence.DTOs;

import com.example.persistence.entities.Employee;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

@Value
public class SalaryDto implements Serializable {

    private Integer salaryId;
    private String employeeName;
    private BigDecimal basicSalary;
    private BigDecimal bonus;
    private BigDecimal deductions;

    private BigDecimal netSalary;
}
