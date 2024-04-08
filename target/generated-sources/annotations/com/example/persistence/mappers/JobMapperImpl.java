package com.example.persistence.mappers;

import com.example.persistence.DTOs.JobDto;
import com.example.persistence.entities.Department;
import com.example.persistence.entities.Job;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-08T14:53:25+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class JobMapperImpl implements JobMapper {

    @Override
    public JobDto jobToJobDto(Job job) {
        if ( job == null ) {
            return null;
        }

        String departmentName = null;
        Integer jobId = null;
        String jobName = null;
        String requirements = null;

        departmentName = jobDepartmentDepartmentName( job );
        jobId = job.getJobId();
        jobName = job.getJobName();
        requirements = job.getRequirements();

        JobDto jobDto = new JobDto( jobId, departmentName, jobName, requirements );

        return jobDto;
    }

    @Override
    public Job jobDtoToJob(JobDto jobDto) {
        if ( jobDto == null ) {
            return null;
        }

        Job job = new Job();

        job.setDepartment( jobDtoToDepartment( jobDto ) );
        job.setJobId( jobDto.getJobId() );
        job.setJobName( jobDto.getJobName() );
        job.setRequirements( jobDto.getRequirements() );

        return job;
    }

    @Override
    public List<JobDto> toJobDtoList(List<Job> jobs) {
        if ( jobs == null ) {
            return null;
        }

        List<JobDto> list = new ArrayList<JobDto>( jobs.size() );
        for ( Job job : jobs ) {
            list.add( jobToJobDto( job ) );
        }

        return list;
    }

    @Override
    public List<Job> toJobList(List<JobDto> jobDtos) {
        if ( jobDtos == null ) {
            return null;
        }

        List<Job> list = new ArrayList<Job>( jobDtos.size() );
        for ( JobDto jobDto : jobDtos ) {
            list.add( jobDtoToJob( jobDto ) );
        }

        return list;
    }

    private String jobDepartmentDepartmentName(Job job) {
        if ( job == null ) {
            return null;
        }
        Department department = job.getDepartment();
        if ( department == null ) {
            return null;
        }
        String departmentName = department.getDepartmentName();
        if ( departmentName == null ) {
            return null;
        }
        return departmentName;
    }

    protected Department jobDtoToDepartment(JobDto jobDto) {
        if ( jobDto == null ) {
            return null;
        }

        Department department = new Department();

        department.setDepartmentName( jobDto.getDepartmentName() );

        return department;
    }
}
