package com.example.persistence.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SalaryDto implements Serializable {

    private Integer salaryId;
    private String employeeName;
    private BigDecimal basicSalary;
    private BigDecimal bonus;
    private BigDecimal deductions;

    private BigDecimal netSalary;
}
