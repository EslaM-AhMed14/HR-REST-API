package com.example.persistence.Service;

import com.example.persistence.DAOs.implementation.PerformanceReviewDAO;
import com.example.persistence.DTOs.PerformancReviewDto;
import com.example.persistence.connect.Database;

import java.util.List;

public class PerformanceServace {
    public static List<PerformancReviewDto> getAllPerformanceReview() {
        return Database.doInTransaction(em -> {
            PerformanceReviewDAO performanceDAO = new PerformanceReviewDAO();
            return performanceDAO.getAllPerformanceReview(em);
        });
    }
}
