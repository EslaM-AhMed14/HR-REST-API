package com.example.persistence.DTOs;


import com.example.persistence.enums.Gender;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.json.bind.annotation.JsonbDateFormat;
import lombok.*;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDto  implements Serializable {
    private Integer employeeId;
    private  String departmentName;
    private Integer departmentId;
    private String firstName;
    private String lastName;

    @JsonbDateFormat("yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateOfBirth;

    @JsonbDateFormat("yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate hireDate;

    private Gender gender;

    private  String jobTitle;
    private String email;
    private String phoneNumber;
    private BigDecimal basicSalary ;


}
