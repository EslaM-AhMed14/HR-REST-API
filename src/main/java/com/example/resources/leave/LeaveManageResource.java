package com.example.resources.leave;


import com.example.exception.ResourceNotFound;
import com.example.persistence.dto.LeaveManagementDto;
import com.example.persistence.service.LeaveManagementService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/leaveManagement")
public class LeaveManageResource {

//    @GET
//    @Produces({MediaType.TEXT_PLAIN})
//    public String getit() {
//        return "Leave Management";
//    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/getAllLeaves")
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

            LeaveManagementService.createLeave(performanceReviewDto);
            Map<String,String > response = new HashMap<>();
            response.put("message","newLeave added successfully");
            return Response.status(Response.Status.OK)
                    .entity(response)
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
            Map<String,String > response = new HashMap<>();
            response.put("message","Leave approved successfully");
            return Response.status(Response.Status.OK)
                    .entity(response)
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
            Map<String,String > response = new HashMap<>();
            response.put("message","Leave Rejected successfully");
            return Response.status(Response.Status.OK)
                    .entity(response)
                    .build();
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage()).build();
        }
    }
}