package com.example;

import com.example.persistence.DTOs.DepartmentDto;
import com.example.persistence.Service.DepartmentService;
import com.example.persistence.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class App {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DB");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        entityManager.getTransaction().begin();
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");

        Salary salary = new Salary();
//        salary.set(new BigDecimal(1000));
        employee.setSalary(salary);
        salary.setEmployee(employee);
        entityManager.persist(employee);
        entityManager.persist(salary);
        entityManager.getTransaction().commit();




    }

}
