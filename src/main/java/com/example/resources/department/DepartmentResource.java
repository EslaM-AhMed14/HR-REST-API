package com.example.resources.department;

import com.example.exception.ErrorMessage;
import com.example.exception.ResourceNotFound;
import com.example.persistence.dto.DepartmentDto;
import com.example.persistence.dto.EmployeeDto;
import com.example.persistence.service.DepartmentService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Path("/department")
public class DepartmentResource {

    @GET
    @Produces({MediaType.TEXT_PLAIN })
    public String getIt() {
        return "Department Resource! ";
    }

    @GET
    @Produces({MediaType.TEXT_PLAIN ,MediaType.APPLICATION_JSON})
    @Path("/getAllDepartments")
    public Response getAllEmployees() {
        List<DepartmentDto> departments = DepartmentService.getAllDepartments();
        if (departments.isEmpty())
            throw new ResourceNotFound("No departments found");

        return Response.status(Response.Status.OK)
                .entity(departments)
                .build();

    }

    @GET
    @Produces({MediaType.TEXT_PLAIN ,MediaType.APPLICATION_JSON})
    @Path("/getDepartmentById/{id}")
    public Response getDepartmentById(@PathParam("id") int id) {
        DepartmentDto departmentDto = DepartmentService.getDepartmentById(id);
        if (departmentDto == null)
            throw new ResourceNotFound("Department not found");

        return Response.status(Response.Status.OK)
                .entity(departmentDto)
                .build();

    }

    @GET
    @Produces({MediaType.TEXT_PLAIN ,MediaType.APPLICATION_JSON})
    @Path("/getDepartmentByName")
    public Response getDepartmentByName(@QueryParam("name") String name) {
        DepartmentDto departmentDto = DepartmentService.getDepartmentByName(name);
        if (departmentDto == null)
            throw new ResourceNotFound("Department not found");

        return Response.status(Response.Status.OK)
                .entity(departmentDto)
                .build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addDepartment")
    public Response addDepartment(DepartmentDto departmentDto) {
        try {
            boolean addedDepartment = DepartmentService.addDepartment(departmentDto);
            if (!addedDepartment)
                throw new ResourceNotFound("Department not added");

            Map<String,String > response = new HashMap<>();
            response.put("message", "Department added successfully");
            return Response.status(Response.Status.CREATED)
                    .entity(response)
                    .build();
        }catch (Exception e){
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404);
            Response response = Response.status(Response.Status.NOT_FOUND)
                    .entity(errorMessage)
                    .build();
            throw new WebApplicationException(response);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateDepartment")
    public Response updateDepartment(DepartmentDto departmentDto) {
        try {
            boolean updatedDepartment = DepartmentService.updateDepartment(departmentDto);
            if (!updatedDepartment)
                throw new ResourceNotFound("Department not updated");

            Map<String,String > response = new HashMap<>();
            response.put("message", "Department updated successfully");
            return Response.status(Response.Status.OK)
                    .entity(response)
                    .build();
        }catch (Exception e){
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404);
            Response response = Response.status(Response.Status.NOT_FOUND)
                    .entity(errorMessage)
                    .build();
            throw new WebApplicationException(response);
        }
    }

    @GET
    @Produces({MediaType.TEXT_PLAIN ,MediaType.APPLICATION_JSON})
    @Path("/getAllEmployeesByDepartment/{id}")
    public Response getAllEmployeesByDepartment(@PathParam("id") int id) {
        try {
            Set<EmployeeDto> employeeDtos = DepartmentService.getAllEmployeesByDepartment(id);
            if (employeeDtos.isEmpty())
                throw new ResourceNotFound("No Employees found");

            return Response.status(Response.Status.OK)
                    .entity(employeeDtos)
                    .build();
        }catch (Exception e){
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404);
            Response response = Response.status(Response.Status.NOT_FOUND)
                    .entity(errorMessage)
                    .build();
            throw new WebApplicationException(response);
        }
    }


    @DELETE
    @Path("/deleteDepartment/{id}")
    public Response deleteDepartment(@PathParam("id") int id) {
        try {
            DepartmentService.deleteDepartment(id);
            Map<String,String > response = new HashMap<>();
            response.put("message", "Department deleted successfully");
            return Response.status(Response.Status.OK)
                    .entity(response)
                    .build();
        }catch (Exception e){
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404);
            Response response = Response.status(Response.Status.NOT_FOUND)
                    .entity(errorMessage)
                    .build();
            throw new WebApplicationException(response);
        }
    }

    @GET
    @Produces({MediaType.TEXT_PLAIN ,MediaType.APPLICATION_JSON})
    @Path("/getAllManager")
    public Response getAllManager() {
        try {
            List<EmployeeDto> employees = DepartmentService.getAllManager();
            if (employees.isEmpty())
                throw new ResourceNotFound("No Manager Found");

            return Response.status(Response.Status.OK)
                    .entity(employees)
                    .build();
        }catch (Exception e){
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404);
            Response response = Response.status(Response.Status.NOT_FOUND)
                    .entity(errorMessage)
                    .build();
            throw new WebApplicationException(response);
        }
    }

}