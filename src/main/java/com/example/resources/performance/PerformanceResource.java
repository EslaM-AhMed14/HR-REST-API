package com.example.resources.performance;

import com.example.exception.ResourceNotFound;
import com.example.persistence.dto.PerformancReviewDto;
import com.example.persistence.service.PerformanceServace;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/performanceReview")
public class PerformanceResource {

    @GET
    public String getIt() {
        return "Performance Review";
    }

    @GET
    @Path("/getAllPerformanceReview")
   @Produces(MediaType.APPLICATION_JSON)
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

    @POST
    @Path("/addPerformanceReview")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerformanceReview(PerformancReviewDto performanceReviewDto) {
        try {
            PerformanceServace.addPerformanceReview(performanceReviewDto);
            Map<String,String > response = new HashMap<>();
            response.put("message","new Performance Review added successfully");
            return Response.status(Response.Status.OK)
                    .entity(response)
                    .build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }


    @GET
    @Path("/ReviewsPage/{pageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReviewsByPage(@PathParam("pageId") int pageId , @Context UriInfo uriInfo) {
        try{
            List<PerformancReviewDto> reviews = PerformanceServace.getEmployeesByPage(pageId);

            if (reviews.isEmpty()) {
                throw new ResourceNotFound("No Employees found");
            }

            for (PerformancReviewDto review : reviews) {

                Link previousPage = Link.fromUri(uriInfo.getBaseUri() + "performanceReview/ReviewsPage/" + (pageId - 1))
                        .rel("previousPage")
                        .build();
                Link nextPage = Link.fromUri(uriInfo.getBaseUri() + "performanceReview/ReviewsPage/" + (pageId + 1))
                        .rel("nextPage")
                        .build();

                review.setLinks( Arrays.asList(previousPage, nextPage) );
            }

            return Response.ok(reviews).build();
        }catch (Exception e){
            Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
            throw new WebApplicationException(response);
        }
    }




}
