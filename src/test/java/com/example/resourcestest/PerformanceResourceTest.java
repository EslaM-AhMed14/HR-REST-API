package com.example.resourcestest;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PerformanceResourceTest {

     @Test
     void testGetAllPerformanceReview() {
         // Arrange
         Client client = ClientBuilder.newClient();

         // Act
         Response response = client.target("http://localhost:9090/HR-Rest/webapi/performanceReview/getAllPerformanceReview")
                 .request(MediaType.APPLICATION_JSON)
                 .get();

         // Assert
         assertEquals(Response.Status.OK.getStatusCode(), response.getStatus(), "Expected status code 200");
         response.close();
         client.close();
     }

    @Test
    void testGetReviewsByPage() {
         // Arrange
        Client client = ClientBuilder.newClient();
        int pageId = 1;

        // Act
        Response response = client.target("http://localhost:9090/HR-Rest/webapi/performanceReview/ReviewsPage/" + pageId)
                .request(MediaType.APPLICATION_JSON)
                .get();
        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus(), "Expected status code 200");
        response.close();
        client.close();
    }
 }
