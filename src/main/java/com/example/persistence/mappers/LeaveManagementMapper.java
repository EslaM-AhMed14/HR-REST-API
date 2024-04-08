package com.example.persistence.mappers;

import com.example.persistence.DTOs.LeaveManagementDto;
import com.example.persistence.entities.LeaveManagement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LeaveManagementMapper {
    LeaveManagementMapper INSTANCE = Mappers.getMapper(LeaveManagementMapper.class);

    @Mapping(source = "employee.firstName", target = "employeeName")
    @Mapping(source = "employee.employeeId", target = "employeeId")
    LeaveManagementDto leaveManagementToLeaveManagementDto(LeaveManagement leaveManagement);

    @Mapping(source = "employeeName", target = "employee.firstName")
    @Mapping(source = "employeeId", target = "employee.employeeId")
    LeaveManagement leaveManagementDtoToLeaveManagement(LeaveManagementDto leaveManagementDto);

    List<LeaveManagement> toLeaveManagementList(List<LeaveManagementDto> leaveManagementDtos);

    List<LeaveManagementDto> toLeaveManagementDtoList(List<LeaveManagement> leaveManagements);
}
