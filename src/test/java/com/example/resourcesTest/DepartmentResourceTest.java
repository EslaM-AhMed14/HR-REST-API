package com.example.resourcesTest;


import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

 class DepartmentResourceTest {

    @Test
     void testGetAllDepartments() {
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:9090/HR-Rest/webapi/department/getAllDepartments")
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertEquals(200, response.getStatus(), "Expected status code 200");
        response.close();
        client.close();
    }
}