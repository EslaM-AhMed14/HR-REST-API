package com.example.CustomException;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class ResourceNotFoundMapper implements ExceptionMapper<ResourceNotFound> {
    @Override
    public Response toResponse(ResourceNotFound exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 404, "probably the id is not found in the database");
        return Response.status(404)
                .entity(errorMessage)
                .build();
    }
}
