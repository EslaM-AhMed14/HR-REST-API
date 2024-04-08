package com.example.persistence.mappers;

import com.example.persistence.DTOs.LeaveManagementDto;
import com.example.persistence.entities.Employee;
import com.example.persistence.entities.LeaveManagement;
import com.example.persistence.enums.LeaveStatus;
import com.example.persistence.enums.LeaveType;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-08T14:53:25+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class LeaveManagementMapperImpl implements LeaveManagementMapper {

    @Override
    public LeaveManagementDto leaveManagementToLeaveManagementDto(LeaveManagement leaveManagement) {
        if ( leaveManagement == null ) {
            return null;
        }

        String employeeName = null;
        Integer leaveId = null;
        LeaveType leaveType = null;
        Date startDate = null;
        Date endDate = null;
        LeaveStatus status = null;
        Date approvalDate = null;

        employeeName = leaveManagementEmployeeFirstName( leaveManagement );
        leaveId = leaveManagement.getLeaveId();
        leaveType = leaveManagement.getLeaveType();
        startDate = leaveManagement.getStartDate();
        endDate = leaveManagement.getEndDate();
        status = leaveManagement.getStatus();
        approvalDate = leaveManagement.getApprovalDate();

        LeaveManagementDto leaveManagementDto = new LeaveManagementDto( leaveId, employeeName, leaveType, startDate, endDate, status, approvalDate );

        return leaveManagementDto;
    }

    @Override
    public LeaveManagement leaveManagementDtoToLeaveManagement(LeaveManagementDto leaveManagementDto) {
        if ( leaveManagementDto == null ) {
            return null;
        }

        LeaveManagement leaveManagement = new LeaveManagement();

        leaveManagement.setEmployee( leaveManagementDtoToEmployee( leaveManagementDto ) );
        leaveManagement.setLeaveId( leaveManagementDto.getLeaveId() );
        leaveManagement.setLeaveType( leaveManagementDto.getLeaveType() );
        leaveManagement.setStartDate( leaveManagementDto.getStartDate() );
        leaveManagement.setEndDate( leaveManagementDto.getEndDate() );
        leaveManagement.setStatus( leaveManagementDto.getStatus() );
        leaveManagement.setApprovalDate( leaveManagementDto.getApprovalDate() );

        return leaveManagement;
    }

    @Override
    public List<LeaveManagement> toLeaveManagementList(List<LeaveManagementDto> leaveManagementDtos) {
        if ( leaveManagementDtos == null ) {
            return null;
        }

        List<LeaveManagement> list = new ArrayList<LeaveManagement>( leaveManagementDtos.size() );
        for ( LeaveManagementDto leaveManagementDto : leaveManagementDtos ) {
            list.add( leaveManagementDtoToLeaveManagement( leaveManagementDto ) );
        }

        return list;
    }

    @Override
    public List<LeaveManagementDto> toLeaveManagementDtoList(List<LeaveManagement> leaveManagements) {
        if ( leaveManagements == null ) {
            return null;
        }

        List<LeaveManagementDto> list = new ArrayList<LeaveManagementDto>( leaveManagements.size() );
        for ( LeaveManagement leaveManagement : leaveManagements ) {
            list.add( leaveManagementToLeaveManagementDto( leaveManagement ) );
        }

        return list;
    }

    private String leaveManagementEmployeeFirstName(LeaveManagement leaveManagement) {
        if ( leaveManagement == null ) {
            return null;
        }
        Employee employee = leaveManagement.getEmployee();
        if ( employee == null ) {
            return null;
        }
        String firstName = employee.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    protected Employee leaveManagementDtoToEmployee(LeaveManagementDto leaveManagementDto) {
        if ( leaveManagementDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setFirstName( leaveManagementDto.getEmployeeName() );

        return employee;
    }
}
