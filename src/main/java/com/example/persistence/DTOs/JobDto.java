package com.example.persistence.DTOs;

import com.example.persistence.entities.Department;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JobDto  implements Serializable {
    private Integer jobId;

    private String departmentProviderName;
    private Integer departmentProviderId;
    private String jobName;
    private String requirements;
}