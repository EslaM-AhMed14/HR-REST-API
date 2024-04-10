package com.example.resourcestest;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SalaryResourceTest {

     @Test
     void testGetBasicSalary() {
         // Arrange
         Client client = ClientBuilder.newClient();
         int employeeId = 27;

         // Act
         Response response = client.target("http://localhost:9090/HR-Rest/webapi/salary/getBasicSalary")
                 .queryParam("employeeId", employeeId)
                 .request(MediaType.APPLICATION_JSON)
                 .get();
         // Assert
         assertEquals(Response.Status.OK.getStatusCode(), response.getStatus(), "Expected status code 200");
         response.close();
         client.close();
     }


    @Test
    void testGetBasicSalaries() {
         // Arrange
        Client client = ClientBuilder.newClient();
        // Act
        Response response = client.target("http://localhost:9090/HR-Rest/webapi/salary/getBasicSalaries")
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus(), "Expected status code 200");
        response.close();
        client.close();
    }

    @Test
    void testGetBasicSalaryRange() {
         // Arrange
        Client client = ClientBuilder.newClient();
        int min = 1000;
        int max = 2000;
        // Act
        Response response = client.target("http://localhost:9090/HR-Rest/webapi/salary/getBasicSalaryRange")
                .queryParam("min", min)
                .queryParam("max", max)
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus(), "Expected status code 200");
        response.close();
        client.close();
    }
 }
