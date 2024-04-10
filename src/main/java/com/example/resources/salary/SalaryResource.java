package com.example.resources.salary;


import com.example.persistence.dto.SalaryDto;
import com.example.persistence.service.SalaryService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/salary")
public class SalaryResource {

    @GET
    public String getIt() {
        return "Salary Resource!";
    }

    @GET
    @Path("/getBasicSalary")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBasicSalary(@QueryParam("employeeId")  int id) {
        try {
            SalaryDto salaryDto =SalaryService.getBasicSalary(id);
            return Response.status(Response.Status.OK)
                    .entity(salaryDto)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();

        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getBasicSalaries")
    public Response getBasicSalaries() {
        try {
           List<SalaryDto>  salaryDtos= SalaryService.getBasicSalaries();
            return Response.status(Response.Status.OK)
                    .entity(salaryDtos)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();

        }
    }



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getBasicSalaryRange")
    public Response getBasicSalaryRange(@QueryParam("min") int min, @QueryParam("max") int max) {
        try {
            List<SalaryDto> salaryDtos = SalaryService.getBasicSalaryRange(min, max);
            return Response.status(Response.Status.OK)
                    .entity(salaryDtos)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();

        }
    }


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/applayBonu")
    public Response bonusForAllEmployees(@QueryParam("percentage") double percentage) {
        try {
            SalaryService.applayBonusForAllEmployees(percentage);
            Map<String,String > response = new HashMap<>();
            response.put("message","Bonus for all employees increased by "+percentage+"%");
            return Response.status(Response.Status.OK)
                    .entity(response)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/applayDeduction")
    public Response deductionsForAllEmployees(@QueryParam("percentage") double percentage) {
        try {
            SalaryService.applayDeductionsForAllEmployees(percentage);
            Map<String,String > response = new HashMap<>();
            response.put("message","Deduction for all employees increased by "+percentage+"%");
            return Response.status(Response.Status.OK)
                    .entity(response)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/applayBounForEmployee")
    public Response bonusForEmployee(@QueryParam("employeeId") int employeeId, @QueryParam("percentage") double percentage) {
        try {
            SalaryService.applayBonusForEmployee(employeeId, percentage);
            Map<String,String > response = new HashMap<>();
            response.put("message","Bonus for employee with id: "+employeeId+" increased by "+percentage+"%");
            return Response.status(Response.Status.OK)
                    .entity(response)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/applayDeductionForEmployee")
    public Response deductionForEmployee(@QueryParam("employeeId") int employeeId, @QueryParam("percentage") double percentage) {
        try {
            SalaryService.applayDeductionsForEmployee(employeeId, percentage);
            Map<String,String > response = new HashMap<>();
            response.put("message","Deduction for employee with id: "+employeeId+" increased by "+percentage+"%");
            return Response.status(Response.Status.OK)
                    .entity(response)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }



}
