package com.example.resources.performance;

import com.example.persistence.DAOs.implementation.PerformanceReviewDAO;
import com.example.persistence.DTOs.PerformancReviewDto;
import com.example.persistence.DTOs.SalaryDto;
import com.example.persistence.Service.PerformanceServace;
import com.example.persistence.Service.SalaryService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/performanceReview")
public class PerformanceResource {

    @GET
    public String getIt() {
        return "Performance Review";
    }

    @GET
    @Path("/getAllPerformanceReview")
    @Produces("application/json")
    public Response getAllPerformanceReview() {
        try {
            List<PerformancReviewDto> performances = PerformanceServace.getAllPerformanceReview();
            return Response.status(Response.Status.OK)
                    .entity(performances)
                    .build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }


}
