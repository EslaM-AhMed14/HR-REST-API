package com.example.persistence.DAOs.implementation;


import com.example.CustomException.ResourceNotFound;
import com.example.persistence.DAOs.interfaces.PerformanceReviewDAOInt;
import com.example.persistence.DTOs.PerformancReviewDto;
import com.example.persistence.connect.Database;
import com.example.persistence.entities.PerformanceReview;
import jakarta.persistence.EntityManager;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PerformanceReviewDAO  implements PerformanceReviewDAOInt {




    @Override
    public boolean save(PerformanceReview performanceReview, EntityManager em) {
        return false;
    }

    @Override
    public Optional<PerformanceReview> get(Integer id, EntityManager em) {
        return Optional.empty();
    }

    @Override
    public boolean update(PerformanceReview performanceReview, EntityManager em) {
        return false;
    }

    @Override
    public void delete(PerformanceReview performanceReview, EntityManager em) {

    }


    public List<PerformanceReview> getAllPerformanceReview(EntityManager em) {
        try{
            return em.createQuery("from  PerformanceReview p", PerformanceReview.class)
                    .getResultList();
        }catch (Exception e) {
            e.getMessage();
            throw new ResourceNotFound("No Performance Review Found");
        }

    }

    public void addPerformanceReview(PerformanceReview performanceReview, EntityManager em) {
        try {
            performanceReview.setReviewDate(new java.sql.Date(new java.util.Date().getTime()));
            em.persist(performanceReview);

        }catch (Exception e) {
            if (e instanceof jakarta.json.bind.JsonbException) {
                throw new RuntimeException("Invalid Performance Rating value. Allowed values are: Excellent, Very_good, Good, Fair, Poor");
            }  else {
                e.getMessage();
                throw new ResourceNotFound("Performance Review not added");
            }
        }
    }

    public List<PerformanceReview> getEmployeesByPage(int pageId, EntityManager em) {
try {
        return em.createQuery("SELECT p FROM PerformanceReview p", PerformanceReview.class)
                    .setFirstResult((pageId-1) * 2)
                    .setMaxResults(2)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotFound("No Performance Review found");
        }
    }
}
