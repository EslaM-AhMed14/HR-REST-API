package com.example.persistence.mappers;



import com.example.persistence.DTOs.PerformancReviewDto;
import com.example.persistence.entities.PerformanceReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PerformanceReviewMapper {
    PerformanceReviewMapper INSTANCE = Mappers.getMapper(PerformanceReviewMapper.class);

    @Mapping(source = "employee.firstName", target = "employeeName")
    PerformancReviewDto performanceReviewToPerformanceReviewDto(PerformanceReview performanceReview);

    @Mapping(source = "employeeName", target = "employee.firstName")
    PerformanceReview performanceReviewDtoToPerformanceReview(PerformancReviewDto performanceReviewDto);
}