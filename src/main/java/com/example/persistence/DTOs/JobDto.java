package com.example.persistence.DTOs;

import com.example.persistence.entities.Department;
import lombok.Value;

import java.io.Serializable;

@Value
public class JobDto  implements Serializable {
    private Integer jobId;

    private String departmentName;
    private String jobName;
    private String requirements;
}
