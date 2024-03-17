package com.vk.demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {
    
    // Add an Exception Handler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {

        // create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        // setting the values for our POJO
        error.setMessage(exc.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp(System.currentTimeMillis());

        // Return a ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); // returns the response (body, status code)
    }

    // Add another exception handler, to catch any exception
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {

        // create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        // setting the values for our POJO
        error.setMessage(exc.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimeStamp(System.currentTimeMillis());

        // Return a ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); // returns the response (body, status code)
    }
}



 /*
  @ExceptionHandler -> Exception handler Method
    public ResponseEntity<StudentErrorResponse> // type of response body
     handleException
     (StudentNotFoundException --> Exception type to handle/catch. So here this means we are catching StudentNotFoundException
     exc)

     Exception exc - < to catch all generic exception
  */