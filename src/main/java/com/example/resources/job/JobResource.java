package com.example.resources.job;

import com.example.exception.ErrorMessage;
import com.example.persistence.dto.JobDto;
import com.example.persistence.service.JobService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/jobOpening")
public class JobResource {

    @GET
    public String getIt() {
        return "Job Opening Resource!";
    }

    @GET
    @Path("/getAllJobOpenings")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllJobOpenings() {
        try {
            List<JobDto> jobDto = JobService.getAllJobOpenings();
            return Response.status(Response.Status.OK)
                    .entity(jobDto)
                    .build();
        } catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage() ,404);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(errorMessage)
                    .build();

        }
    }


    @POST
    @Path("/createJobOpening")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createJobOpening(JobDto jobDto) {
        try {
            JobService.createJobOpening(jobDto);
            Map<String,String > response = new HashMap<>();
            response.put("message","new Job Opening added successfully");
            return Response.status(Response.Status.OK)
                    .entity(response)
                    .build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/updateJobOpening")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateJobOpening(JobDto jobDto) {
        try {
            JobService.updateJobOpening(jobDto);
            Map<String,String > response = new HashMap<>();
            response.put("message","Job Opening updated successfully");
            return Response.status(Response.Status.OK)
                    .entity(response)
                    .build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/deleteJobOpening")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteJobOpening(@QueryParam("id") int id) {
        try {
            JobService.deleteJobOpening(id);
            Map<String,String > response = new HashMap<>();
            response.put("message","Job Opening deleted successfully");
            return Response.status(Response.Status.OK)
                    .entity(response)
                    .build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/getJobOpeningForDepartment")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobOpeningForDepartment(@QueryParam("departmentId") int departmentId) {
        try {
            List<JobDto> jobDto = JobService.getJobOpeningForDepartment(departmentId);
            return Response.status(Response.Status.OK)
                    .entity(jobDto)
                    .build();
        } catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage() ,404);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(errorMessage)
                    .build();

        }
    }



}