package com.example.persistence.dao.impl;

import com.example.exception.ResourceNotFound;
import com.example.persistence.dao.interfaces.LeavManagementDAOInt;
import com.example.persistence.entity.LeaveManagement;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.Period;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class LeaveManagementDAO  implements LeavManagementDAOInt {


    public List<LeaveManagement> getAllPerformanceReview(EntityManager em) {
        try {
            return em.createQuery("FROM LeaveManagement l", LeaveManagement.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("No Vications Review Found");
        }
    }



    @Override
    public boolean save(LeaveManagement leaveManagement, EntityManager em) {

            int days = areDatesValid(leaveManagement);

            em.persist(leaveManagement);
            return true;

    }

    @Override
    public Optional<LeaveManagement> get(Integer id, EntityManager em) {
        return Optional.ofNullable(em.find(LeaveManagement.class, id));
    }

    @Override
    public boolean update(LeaveManagement leaveManagement, EntityManager em) {
        return false;
    }

    @Override
    public void delete(LeaveManagement leaveManagement, EntityManager em) {

    }

    public int areDatesValid(LeaveManagement leaveManagement) {
        Date startDate = leaveManagement.getStartDate();
        Date endDate = leaveManagement.getEndDate();


        if (startDate == null ) {
            throw new ResourceNotFound("Cannot have null start date");
        }
        if(endDate == null){
            throw new IllegalArgumentException("Cannot have null end date");
        }

        LocalDate startLocalDate;
        LocalDate endLocalDate;
        try {
             startLocalDate = startDate.toLocalDate();
             endLocalDate = endDate.toLocalDate();

        }catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Invalid date format");
        }



        if (endLocalDate.isBefore(startLocalDate)) {

            throw new IllegalArgumentException("End date cannot be before start date");
        }


        Period period = Period.between(startLocalDate, endLocalDate);

        int days = period.getDays();


        if(days>30)
            throw new ResourceNotFound("Cannot have more than 30 days of leave");


        return days;
    }


}
