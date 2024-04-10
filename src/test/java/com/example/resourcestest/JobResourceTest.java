package com.example.resourcestest;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JobResourceTest {

    @Test
    void testGetAllJobOpenings() {
        // Arrange
        Client client = ClientBuilder.newClient();

        // Act
        Response response = client.target("http://localhost:9090/HR-Rest/webapi/jobOpening/getAllJobOpenings")
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus(), "Expected status code 200");
        response.close();
        client.close();

    }


    @Test
    void testGetJobOpeningForDepartment() {
        // Arrange
        Client client = ClientBuilder.newClient();
        int departmentId = 1;
        // Act
        Response response = client.target("http://localhost:9090/HR-Rest/webapi/jobOpening/getJobOpeningForDepartment")
                .queryParam("departmentId", departmentId)
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus(), "Expected status code 200");
        response.close();
        client.close();
    }

}
