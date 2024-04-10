package com.example.resourcestest;


import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

 class DepartmentResourceTest {

    @Test
     void testGetAllDepartments() {
        // Arrange
        Client client = ClientBuilder.newClient();
        // Act
        Response response = client.target("http://localhost:9090/HR-Rest/webapi/department/getAllDepartments")
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Assert
        assertEquals(200, response.getStatus(), "Expected status code 200");
        response.close();
        client.close();
    }

     @Test
     void testGetDepartmentById() {
        // Arrange
         Client client = ClientBuilder.newClient();
         int departmentId = 1;
         // Act
         Response response = client.target("http://localhost:9090/HR-Rest/webapi/department/getDepartmentById/" + departmentId)
                 .request(MediaType.APPLICATION_JSON)
                 .get();
         // Assert
         assertEquals(200, response.getStatus(), "Expected status code 200");
         response.close();
         client.close();
     }


     @Test
     void testGetDepartmentByName() {
        // Arrange
         Client client = ClientBuilder.newClient();
         String departmentName = "cs";
         // Act
         Response response = client.target("http://localhost:9090/HR-Rest/webapi/department/getDepartmentByName?name="+ departmentName)
                 .request(MediaType.APPLICATION_JSON)
                 .get();
         // Assert
         assertEquals(200, response.getStatus(), "Expected status code 200");
         response.close();
         client.close();
     }

     @Test
     void testGetAllEmployeesByDepartment() {
        // Arrange
         Client client = ClientBuilder.newClient();
         int departmentId = 1;
         // Act
         Response response = client.target("http://localhost:9090/HR-Rest/webapi/department/getAllEmployeesByDepartment/" + departmentId)
                 .request(MediaType.APPLICATION_JSON)
                 .get();
         // Assert
         assertEquals(Response.Status.OK.getStatusCode(), response.getStatus(), "Expected status code 200");
         response.close();
         client.close();
     }

     @Test
     void testGetAllManager() {
         // Arrange
         Client client = ClientBuilder.newClient();

         // Act
         Response response = client.target("http://localhost:9090/HR-Rest/webapi/department/getAllManager")
                 .request(MediaType.APPLICATION_JSON)
                 .get();
        // Assert
         assertEquals(Response.Status.OK.getStatusCode(), response.getStatus(), "Expected status code 200");
         response.close();
         client.close();
     }


}