package com.example.persistence.mappers;

import com.example.persistence.DTOs.SalaryDto;
import com.example.persistence.entities.Employee;
import com.example.persistence.entities.Salary;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-08T14:53:25+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class SalaryMapperImpl implements SalaryMapper {

    @Override
    public SalaryDto salaryToSalaryDto(Salary salary) {
        if ( salary == null ) {
            return null;
        }

        String employeeName = null;
        Integer salaryId = null;
        BigDecimal basicSalary = null;
        BigDecimal bonus = null;
        BigDecimal deductions = null;

        employeeName = salaryEmployeeFirstName( salary );
        salaryId = salary.getSalaryId();
        basicSalary = salary.getBasicSalary();
        bonus = salary.getBonus();
        deductions = salary.getDeductions();

        SalaryDto salaryDto = new SalaryDto( salaryId, employeeName, basicSalary, bonus, deductions );

        return salaryDto;
    }

    @Override
    public Salary salaryDtoToSalary(SalaryDto salaryDto) {
        if ( salaryDto == null ) {
            return null;
        }

        Salary salary = new Salary();

        salary.setEmployee( salaryDtoToEmployee( salaryDto ) );
        salary.setSalaryId( salaryDto.getSalaryId() );
        salary.setBasicSalary( salaryDto.getBasicSalary() );
        salary.setBonus( salaryDto.getBonus() );
        salary.setDeductions( salaryDto.getDeductions() );

        return salary;
    }

    @Override
    public List<Salary> toSalaryList(List<SalaryDto> salaryDtos) {
        if ( salaryDtos == null ) {
            return null;
        }

        List<Salary> list = new ArrayList<Salary>( salaryDtos.size() );
        for ( SalaryDto salaryDto : salaryDtos ) {
            list.add( salaryDtoToSalary( salaryDto ) );
        }

        return list;
    }

    @Override
    public List<SalaryDto> toSalaryDtoList(List<Salary> salaries) {
        if ( salaries == null ) {
            return null;
        }

        List<SalaryDto> list = new ArrayList<SalaryDto>( salaries.size() );
        for ( Salary salary : salaries ) {
            list.add( salaryToSalaryDto( salary ) );
        }

        return list;
    }

    private String salaryEmployeeFirstName(Salary salary) {
        if ( salary == null ) {
            return null;
        }
        Employee employee = salary.getEmployee();
        if ( employee == null ) {
            return null;
        }
        String firstName = employee.getFirstName();
        if ( firstName == null ) {
            return null;
        }
        return firstName;
    }

    protected Employee salaryDtoToEmployee(SalaryDto salaryDto) {
        if ( salaryDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setFirstName( salaryDto.getEmployeeName() );

        return employee;
    }
}
