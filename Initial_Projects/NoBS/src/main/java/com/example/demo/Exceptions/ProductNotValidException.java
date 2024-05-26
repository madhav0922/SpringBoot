package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;

// Lecture 16: Exception Handling - Part 3 - Custom Exception handling globally
public class ProductNotValidException extends CustomBaseException {

    public ProductNotValidException(String message) {
        super(HttpStatus.BAD_REQUEST, new SimpleResponse(message));
    }
}
