package com.example.persistence.Service;

import com.example.persistence.DAOs.implementation.SalaryDAO;
import com.example.persistence.DTOs.SalaryDto;
import com.example.persistence.connect.Database;
import com.example.persistence.entities.Salary;
import com.example.persistence.mappers.SalaryMapper;
import org.mapstruct.Mapping;
import org.mapstruct.ap.internal.model.Mapper;

import java.util.List;

public class SalaryService {
    public SalaryService() {
    }

    public static SalaryDto getBasicSalary(int employeeId) {
        return Database.doInTransaction(em -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            Salary salary = salaryDAO.getBasicSalary(employeeId, em);
            return SalaryMapper.INSTANCE.salaryToSalaryDto(salary);
        });
    }

    public static List<SalaryDto> getBasicSalaries() {
        return Database.doInTransaction(em -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            List<Salary> salaries = salaryDAO.getBasicSalaries(em);
            return SalaryMapper.INSTANCE.toSalaryDtoList(salaries);
        });
    }

    public static List<SalaryDto> getBasicSalaryRange(int min, int max) {
        return Database.doInTransaction(em -> {
            SalaryDAO salaryDAO = new SalaryDAO();
            List<Salary> salaries = salaryDAO.getBasicSalaryRange(min, max, em);
            return SalaryMapper.INSTANCE.toSalaryDtoList(salaries);
        });
    }
}
