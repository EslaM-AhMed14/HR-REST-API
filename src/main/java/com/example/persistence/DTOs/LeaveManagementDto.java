package com.example.persistence.DTOs;

import com.example.persistence.entities.Employee;
import com.example.persistence.enums.LeaveStatus;
import com.example.persistence.enums.LeaveType;
import lombok.Value;

import java.io.Serializable;
import java.sql.Date;

@Value
public class LeaveManagementDto implements Serializable {
    private Integer leaveId;
    private String employeeName;
    private LeaveType leaveType;
    private Date startDate;
    private Date endDate;
    private LeaveStatus status;
    private Date approvalDate;
}
