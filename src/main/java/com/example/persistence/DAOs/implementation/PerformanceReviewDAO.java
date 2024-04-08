package com.example.persistence.DAOs.implementation;


import com.example.persistence.DTOs.PerformancReviewDto;
import com.example.persistence.connect.Database;
import jakarta.persistence.EntityManager;

import java.util.Date;
import java.util.List;

public class PerformanceReviewDAO  {


    public List<PerformancReviewDto> getAllPerformanceReview(EntityManager em) {
        return em.createQuery("from  PerformanceReview p", PerformancReviewDto.class)
                .getResultList();
    }
}
