package com.example.persistence.mappers;

import com.example.persistence.DTOs.PerformancReviewDto;
import com.example.persistence.entities.Employee;
import com.example.persistence.entities.PerformanceReview;
import com.example.persistence.enums.PerformanceReating;
import java.sql.Date;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-08T14:53:25+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class PerformanceReviewMapperImpl implements PerformanceReviewMapper {

    @Override
    public PerformancReviewDto performanceReviewToPerformanceReviewDto(PerformanceReview performanceReview) {
        if ( performanceReview == null ) {
            return null;
        }

        String employeeName = null;
        Integer reviewId = null;
        Date reviewDate = null;
        PerformanceReating performanceRating = null;

        employeeName = performanceReviewEmployeeFirstName( performanceReview );
        reviewId = performanceReview.getReviewId();
        reviewDate = performanceReview.getReviewDate();
        performanceRating = performanceReview.getPerformanceRating();

        PerformancReviewDto performancReviewDto = new PerformancReviewDto( reviewId, employeeName, reviewDate, performanceRating );

        return performancReviewDto;
    }

    @Override
    public PerformanceReview performanceReviewDtoToPerformanceReview(PerformancReviewDto performanceReviewDto) {
        if ( performanceReviewDto == null ) {
            return null;
        }

        PerformanceReview performanceReview = new PerformanceReview();

        performanceReview.setEmployee( performancReviewDtoToEmployee( performanceReviewDto ) );
        performanceReview.setReviewId( performanceReviewDto.getReviewId() );
        performanceReview.setReviewDate( performanceReviewDto.getReviewDate() );
        performanceReview.setPerformanceRating( performanceReviewDto.getPerformanceRating() );

        return performanceReview;
    }

    private String performanceReviewEmployeeFirstName(PerformanceReview performanceReview) {
        if ( performanceReview == null ) {
            return null;
        }
        Employee employee = performanceReview.getEmployee();
        if ( employee == null ) {
            return null;
        }
        String firstName = employee.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    protected Employee performancReviewDtoToEmployee(PerformancReviewDto performancReviewDto) {
        if ( performancReviewDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setFirstName( performancReviewDto.getEmployeeName() );

        return employee;
    }
}
