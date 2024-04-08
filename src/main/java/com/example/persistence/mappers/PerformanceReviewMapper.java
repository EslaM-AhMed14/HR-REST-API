package com.example.persistence.mappers;



import com.example.persistence.DTOs.EmployeeDto;
import com.example.persistence.DTOs.PerformancReviewDto;
import com.example.persistence.entities.Employee;
import com.example.persistence.entities.PerformanceReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PerformanceReviewMapper {
    PerformanceReviewMapper INSTANCE = Mappers.getMapper(PerformanceReviewMapper.class);

    @Mapping(source = "employee.firstName", target = "employeeName")
    @Mapping(source = "employee.firstName", target = "employeeId")
    PerformancReviewDto performanceReviewToPerformanceReviewDto(PerformanceReview performanceReview);

    @Mapping(source = "employeeName", target = "employee.firstName")
    @Mapping(source = "employeeId", target = "employee.employeeId")
    PerformanceReview performanceReviewDtoToPerformanceReview(PerformancReviewDto performanceReviewDto);

    List<PerformanceReview> toPerformancReviewDtoList(List<Employee> employees);

    List<PerformancReviewDto> toPerformancReviewList(List<EmployeeDto> employeeDtos);
}