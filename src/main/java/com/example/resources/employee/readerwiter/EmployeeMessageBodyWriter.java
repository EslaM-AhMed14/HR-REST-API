//package com.example.resources.Employee.ReaderAndWiter;
//
//import com.example.persistence.DTOs.EmployeeDto;
//import jakarta.ws.rs.Produces;
//import jakarta.ws.rs.WebApplicationException;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.MultivaluedMap;
//import jakarta.ws.rs.ext.MessageBodyWriter;
//import jakarta.ws.rs.ext.Provider;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Type;
//
//@Provider
//@Produces(MediaType.TEXT_PLAIN)
//public class EmployeeMessageBodyWriter implements MessageBodyWriter<EmployeeDto> {
//
//
//    @Override
//    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
//        return EmployeeDto.class.isAssignableFrom(type);
//    }
//
//    @Override
//    public void writeTo(EmployeeDto employeeDto, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
//        entityStream.write(employeeDto.toString().getBytes());
//    }
//}
