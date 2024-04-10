package com.example.resources.employee;

import com.example.exception.ErrorMessage;
import com.example.exception.ResourceNotFound;
import com.example.persistence.dto.EmployeeDto;
import com.example.persistence.service.EmployeeService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.Arrays;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("employee")
public class EmployeeResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces({MediaType.TEXT_PLAIN })
    public String getIt() {
        return "Hello, To Employee Resource!";
    }

    @GET
    @Produces({MediaType.TEXT_PLAIN ,MediaType.APPLICATION_JSON})
    @Path("getAllEmployees")
    public Response getAllEmployees() {
        List<EmployeeDto> employees = EmployeeService.getAllEmployees();
        if (employees.isEmpty())
            throw new ResourceNotFound("No Employees found");

        return Response.status(Response.Status.OK)
                    .entity(employees)
                    .build();

    }

    @GET
    @Produces({MediaType.TEXT_PLAIN ,MediaType.APPLICATION_JSON , MediaType.APPLICATION_XML})
    @Path("getEmployeeById/{id}")
    public Response getEmployeeById(@PathParam("id") int id) {
        EmployeeDto employeeDto = EmployeeService.getEmployeeById(id);
        if (employeeDto == null){
            ErrorMessage errorMessage = new ErrorMessage("Employee not found", 404, "probably the id is not found in the database");
          Response response = Response.status(Response.Status.NOT_FOUND)
                    .entity(errorMessage)
                    .build();
            throw new WebApplicationException(response);
        }


        return Response.status(Response.Status.OK)
                    .entity(employeeDto)
                    .build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("addEmployee")
    public Response addEmployee(EmployeeDto employeeDto) {
        boolean addedEmployee = EmployeeService.addEmployee(employeeDto);
        if (!addedEmployee){
            ErrorMessage errorMessage = new ErrorMessage("Employee not add", 404, "probably the department is not found in the database");
            Response response = Response.status(Response.Status.NOT_FOUND)
                    .entity(errorMessage)
                    .build();
            throw new WebApplicationException(response);
        }

        return Response.status(Response.Status.CREATED)
                    .entity("Employee added successfully")
                    .build();

    }

    @DELETE
    @Path("deleteEmployee/{id}")
    public Response deleteEmployee(@PathParam("id") int id) {
        boolean done =  EmployeeService.deleteEmployee(id);
        if (!done)
            throw new ResourceNotFound("Employee not found");

        return Response.status(Response.Status.OK)
                .entity("Employee deleted successfully")
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/updateEmployee")
    public Response updateEmployee(EmployeeDto employeeDto) {
        try{
        boolean updatedEmployee = EmployeeService.updateEmployee(employeeDto);
        if (!updatedEmployee)
            throw new ResourceNotFound("Employee not updated");

        return Response.status(Response.Status.OK)
                .entity("Employee updated successfully")
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
    @Path("employeesPage/{pageId}")
    public Response getEmployeesByPage(@PathParam("pageId") int pageId , @Context UriInfo uriInfo) {
        try{
            List<EmployeeDto> employees = EmployeeService.getEmployeesByPage(pageId);

            if (employees.isEmpty()) {
                throw new ResourceNotFound("No Employees found");
            }

            for (EmployeeDto employee : employees) {

                Link previousPage = Link.fromUri(uriInfo.getBaseUri() + "employee/employeesPage/" + (pageId - 1))
                        .rel("previousPage")
                        .build();
                Link nextPage = Link.fromUri(uriInfo.getBaseUri() + "employee/employeesPage/" + (pageId + 1))
                        .rel("nextPage")
                        .build();

                employee.setLinks( Arrays.asList(previousPage, nextPage) );
            }

            return Response.ok(employees).build();
        }catch (Exception e){
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404);
            Response response = Response.status(Response.Status.NOT_FOUND)
                    .entity(errorMessage)
                    .build();
            throw new WebApplicationException(response);
        }
    }

}

