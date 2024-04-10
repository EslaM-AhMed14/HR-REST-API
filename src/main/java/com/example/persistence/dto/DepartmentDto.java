package com.example.persistence.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepartmentDto implements Serializable {
    private Integer departmentId;
    private String departmentName;
    private String managerName;
    private Integer managerId;
    private String managerJobTitle;
    private Boolean isHead;


}
