package com.example.resources.salary;


import com.example.CustomException.ErrorMessage;
import com.example.persistence.DTOs.SalaryDto;
import com.example.persistence.Service.SalaryService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/salary")
public class SalaryResource {

    @GET
    public String getIt() {
        return "Salary Resource!";
    }

    @GET
    @Path("/getBasicSalary")
    public Response getBasicSalary(@QueryParam("employeeId")  int id) {
        try {
            SalaryDto salaryDto =SalaryService.getBasicSalary(id);
            return Response.status(Response.Status.OK)
                    .entity(salaryDto)
                    .build();
        } catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage() ,404);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(errorMessage)
                    .build();

        }
    }


    @GET
    @Produces("application/json")
    @Path("/getBasicSalaries")
    public Response getBasicSalaries() {
        try {
           List<SalaryDto>  salaryDtos= SalaryService.getBasicSalaries();
            return Response.status(Response.Status.OK)
                    .entity(salaryDtos)
                    .build();
        } catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage() ,404);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(errorMessage)
                    .build();

        }
    }


    @GET
    @Produces("application/json")
    @Path("/getBasicSalaryRange")
    public Response getBasicSalaryRange(@QueryParam("min") int min, @QueryParam("max") int max) {
        try {
            List<SalaryDto> salaryDtos = SalaryService.getBasicSalaryRange(min, max);
            return Response.status(Response.Status.OK)
                    .entity(salaryDtos)
                    .build();
        } catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage() ,404);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(errorMessage)
                    .build();

        }
    }






}
