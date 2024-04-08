package com.example.persistence.mappers;


import com.example.persistence.DTOs.JobDto;
import com.example.persistence.entities.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public  interface  JobMapper {

    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    @Mapping(source = "department.departmentName", target = "departmentName")
    JobDto jobToJobDto(Job job);

    @Mapping(source = "departmentName", target = "department.departmentName")
    Job jobDtoToJob(JobDto jobDto);

    List<JobDto> toJobDtoList(List<Job> jobs);

    List<Job> toJobList(List<JobDto> jobDtos);
}
