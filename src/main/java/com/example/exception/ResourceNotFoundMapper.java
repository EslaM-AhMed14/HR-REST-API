package com.example.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundMapper implements ExceptionMapper<ResourceNotFound> {
    @Override
    public Response toResponse(ResourceNotFound exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 404);
        return Response.status(404)
                .entity(errorMessage)
                .build();
    }



}
