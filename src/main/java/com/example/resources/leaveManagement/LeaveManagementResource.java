package com.example.resources.leaveManagement;


import com.example.CustomException.ResourceNotFound;
import com.example.persistence.DTOs.LeaveManagementDto;
import com.example.persistence.DTOs.PerformancReviewDto;
import com.example.persistence.Service.LeaveManagementService;
import com.example.persistence.Service.PerformanceServace;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("leaveManagement")
public class LeaveManagementResource {

    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public String getLeaveManagement() {
        return "Leave Management";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Path("getAllLeaves")
    public Response getAllLeaves() {
            List<LeaveManagementDto> leaves = LeaveManagementService.getAllLeaves();
            if(leaves.isEmpty())
                throw new ResourceNotFound("No Leaves found");
            return Response.status(Response.Status.OK)
                    .entity(leaves)
                    .build();

    }



    @POST
    @Path("/createLeave")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createLeave(LeaveManagementDto performanceReviewDto) {
        try {
            System.out.println("resources Leave");
            LeaveManagementService.createLeave(performanceReviewDto);
            return Response.status(Response.Status.OK)
                    .entity("newLeave added successfully")
                    .build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }


    @PUT
    @Path("/approveLeaveByManager")
    @Produces(MediaType.APPLICATION_JSON)
    public Response approveLeave(@QueryParam("leaveId") Integer leaveId , @QueryParam("managerId") Integer managerId) {
        try {
            LeaveManagementService.approveLeave(leaveId, managerId);
            return Response.status(Response.Status.OK)
                    .entity("Leave approved successfully")
                    .build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }



    @PUT
    @Path("/rejectLeaveByManager")
    @Produces(MediaType.APPLICATION_JSON)
    public Response rejectLeave(@QueryParam("leaveId") Integer leaveId , @QueryParam("managerId") Integer managerId) {
        try {
            LeaveManagementService.rejectLeave(leaveId, managerId);
            return Response.status(Response.Status.OK)
                    .entity("Leave Rejected successfully")
                    .build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }
}
