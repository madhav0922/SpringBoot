package com.example.demo.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Lecture 16: Exception Handling - Part 3 - Custom Exception handling globally
@ControllerAdvice // This annotation catches all the exceptions thrown by all the controllers across the entire system.
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomBaseException.class)
    public ResponseEntity<SimpleResponse> handleCustomBaseException(CustomBaseException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getSimpleResponse());
    }
}
