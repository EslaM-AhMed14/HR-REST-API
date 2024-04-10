package com.example.resourcestest;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LeavResourcesTest {

     @Test
     void testGetAllLeaves() {
         // Arrange
         Client client = ClientBuilder.newClient();
         // Act
         Response response = client.target("http://localhost:9090/HR-Rest/webapi/leaveManagement/getAllLeaves")
                 .request(MediaType.APPLICATION_JSON)
                 .get();
        // Assert
         assertEquals(Response.Status.OK.getStatusCode(), response.getStatus(), "Expected status code 200");
         response.close();
         client.close();
     }
}
