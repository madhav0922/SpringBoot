package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;

// Lecture 15/16: Exception Handling - Part 2/3 - Custom Exception at controller level / global
// changed from extends RunTimeException to CustomBaseException to handle the error message better in lecture 16.
public class ProductNotFoundException extends CustomBaseException {
    public ProductNotFoundException() {
        super(HttpStatus.BAD_REQUEST, new SimpleResponse("Product Not Found."));
    }
}
