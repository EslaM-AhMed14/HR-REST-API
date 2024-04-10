package com.example.persistence.dao.impl;


import com.example.persistence.dao.interfaces.JobDAOInt;
import com.example.persistence.entity.Job;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JobDAO  implements JobDAOInt {


    public List<Job> getAllJobs(EntityManager em) {
        try {
            return em.createQuery("FROM Job", Job.class)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("No Jobs Found");
        }
    }

    @Override
    public boolean save(Job job, EntityManager em) {
        try {
            em.persist(job);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Job not Created");
        }
    }

    @Override
    public Optional<Job> get(Integer id, EntityManager em) {
        try {
            return Optional.ofNullable(em.find(Job.class, id));
        } catch (Exception e) {
            throw new RuntimeException("Job not Found");
        }
    }

    @Override
    public boolean update(Job job, EntityManager em) {
        try {
            em.merge(job);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Job not Updated");
        }
    }

    @Override
    public void delete(Job job, EntityManager em) {
        try {
            em.remove(job);
        } catch (Exception e) {
            throw new RuntimeException("Job not Deleted");
        }

    }

    public List<Job> getJobOpeningForDepartment(int departmentId, EntityManager em) {
        try {
            return em.createQuery("FROM Job WHERE department.departmentId = :departmentId", Job.class)
                    .setParameter("departmentId", departmentId)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("No Jobs Found");
        }
    }
}
