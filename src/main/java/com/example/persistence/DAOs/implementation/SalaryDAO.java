package com.example.persistence.DAOs.implementation;


import com.example.persistence.DAOs.interfaces.SalaryDAOInt;
import com.example.persistence.entities.Salary;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class SalaryDAO  implements SalaryDAOInt {


    @Override
    public boolean save(Salary salary, EntityManager em) {
        em.persist(salary);
        Salary salary1 = em.find(Salary.class, salary.getSalaryId());
        if (salary1 == null) {
            return false;
        }
        return true;
    }

    @Override
    public Optional<Salary> get(Integer id, EntityManager em) {
        return Optional.empty();
    }

    @Override
    public boolean update(Salary salary, EntityManager em) {

        try {
            Salary salary1 = em.find(Salary.class, salary.getSalaryId());
            if (salary1 == null) {
                return false;
            }
            em.merge(salary);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public void delete(Salary salary, EntityManager em) {

    }

    public Salary getSalaryByEmployeeId(Integer employeeId, EntityManager em) {
        return em.createQuery("SELECT s FROM Salary s WHERE s.employee.employeeId = :employeeId", Salary.class)
                .setParameter("employeeId", employeeId)
                .getSingleResult();
    }

    public Salary getBasicSalary(Integer employeeId, EntityManager em) {
       try {
           Salary salary = em.createQuery("SELECT s FROM Salary s WHERE s.employee.employeeId = :employeeId", Salary.class)
                   .setParameter("employeeId", employeeId)
                   .getSingleResult();

                return salary;
       }catch (NoResultException e){
           throw new NoResultException("No salary found for employee with id: " + employeeId);
       }catch (NonUniqueResultException e){
           throw new NonUniqueResultException("Multiple salaries found for employee with id: " + employeeId);
       }

    }

    public List<Salary> getBasicSalaries(EntityManager em) {
        try {
            return em.createQuery("SELECT s FROM Salary s", Salary.class)
                    .getResultList();
        }catch (NoResultException e){
            throw new NoResultException("No salaries found");
        }
    }

    public List<Salary> getBasicSalaryRange(int min, int max, EntityManager em) {
        try {
            return em.createQuery("SELECT s FROM Salary s WHERE s.basicSalary BETWEEN :min AND :max", Salary.class)
                    .setParameter("min", min)
                    .setParameter("max", max)
                    .getResultList();
        }catch (NoResultException e){
            throw new NoResultException("No salaries found in the range: " + min + " - " + max);
        }
    }

    public void applayBonusForAllEmployees(double percentage, EntityManager em) {
        try {
            List<Salary> salaries = em.createQuery("SELECT s FROM Salary s", Salary.class).getResultList();

            for (Salary salary : salaries) {
                BigDecimal newBonus = salary.getBasicSalary().multiply(BigDecimal.valueOf(percentage));
                if (newBonus.compareTo(BigDecimal.valueOf(1000)) > 0) {
                    salary.setBonus(BigDecimal.valueOf(1000));
                } else {
                    salary.setBonus(newBonus);
                }
                em.merge(salary);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to apply bonus", e);
        }
    }

    public void applayDeductionsForAllEmployees(double percentage, EntityManager em) {
        try {
            em.createQuery("UPDATE Salary s SET s.deductions = s.basicSalary * :percentage")
                    .setParameter("percentage", percentage)
                    .executeUpdate();
        } catch (Exception e) {
            throw new NoResultException("No salaries found");
        }
    }

    public void applayBonusForEmployee(int employeeId, double percentage, EntityManager em) {

        try {
            Salary salary = em.createQuery("SELECT s FROM Salary s WHERE s.employee.employeeId = :employeeId", Salary.class)
                    .setParameter("employeeId", employeeId)
                    .getSingleResult();
            BigDecimal newBonus = salary.getBasicSalary().multiply(BigDecimal.valueOf(percentage));
            if (newBonus.compareTo(BigDecimal.valueOf(1000)) > 0) {
                salary.setBonus(BigDecimal.valueOf(1000));
            } else {
                salary.setBonus(newBonus);
            }
            em.merge(salary);
        } catch (NoResultException e) {
            throw new NoResultException("No salary found for employee with id: " + employeeId);
        } catch (NonUniqueResultException e) {
            throw new NonUniqueResultException("Multiple salaries found for employee with id: " + employeeId);
        }

    }

    public void applayDeductionsForEmployee(int employeeId, double percentage, EntityManager em) {
        try {
            em.createQuery("UPDATE Salary s SET s.deductions = s.basicSalary * :percentage WHERE s.employee.employeeId = :employeeId")
                    .setParameter("percentage", percentage)
                    .setParameter("employeeId", employeeId)
                    .executeUpdate();
        } catch (NoResultException e) {
            throw new NoResultException("No salary found for employee with id: " + employeeId);
        } catch (NonUniqueResultException e) {
            throw new NonUniqueResultException("Multiple salaries found for employee with id: " + employeeId);
        }
    }


}
