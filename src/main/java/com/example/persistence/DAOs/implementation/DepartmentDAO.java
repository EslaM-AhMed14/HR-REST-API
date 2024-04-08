package com.example.persistence.DAOs.implementation;


import com.example.persistence.DAOs.interfaces.DepartmentDAOInt;
import com.example.persistence.entities.Department;
import com.example.persistence.entities.Employee;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class DepartmentDAO implements DepartmentDAOInt {

    @Override
    public boolean save(Department department, EntityManager em)  throws RuntimeException{
        Department department1 = getDepartmentByName(department.getDepartmentName(), em);
        if (department1 != null) {
            throw new RuntimeException("Department already exists");
        }
        em.persist(department);
        Department department2 = em.find(Department.class, department.getDepartmentId());
        if (department2 == null) {
            return false;
        }
        return true;
    }

    @Override
    public Optional<Department> get(Integer id, EntityManager em) {
        Department department = em.find(Department.class, id);
        return Optional.ofNullable(department);

    }

    @Override
    public boolean update(Department department, EntityManager em) {
        try {

            em.merge(department);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public void delete(Department department, EntityManager em) {
            em.remove(department);
    }

    @Override
    public List<Department> getAllDepartments(EntityManager em) {
      return  em.createQuery("SELECT d FROM Department d", Department.class).getResultList();
    }

    @Override
    public boolean DeleteDepartmentById(Integer id, EntityManager em) {
        try {
            Department department = em.find(Department.class, id);
            Set<Employee> employees = department.getEmployees();
            em.remove(department);
            employees.forEach(employee -> {
                employee.setDepartment(null);
                em.merge(employee);
            });
            return true;
        }catch (Exception e) {
            return false;
        }


    }

    @Override
    public  Department getDepartmentByName(String name, EntityManager em) {
        try {
            Department department = em.createQuery("SELECT d FROM Department d WHERE LOWER( d.departmentName) = LOWER(:name)", Department.class)
                    .setParameter("name", name)
                    .getSingleResult();

            return department;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public boolean isManager(Integer id, EntityManager em) {
        List<Department> department = getAllDepartments(em);

       return department.stream().filter(department1 -> department1.getEmployee().getEmployeeId().equals(id))
                .findFirst().orElse(null) != null;
    }


    public Set<Employee> getAllEmployeesByDepartment(int id, EntityManager em) {
        Department department = em.find(Department.class, id);

        Set<Employee> employees =  department.getEmployees();
        int i =0 ;
        for (Employee employee : employees) {
            System.out.println( "**+"+ i++ +" -"+employee.getFirstName());
        }
        return employees;
    }
}
