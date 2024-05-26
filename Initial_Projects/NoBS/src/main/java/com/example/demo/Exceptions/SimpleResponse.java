package com.example.demo.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // So that in one line we could pass it in and create the object.
// This basically creates an all args constructor.
public class SimpleResponse {
    private String message;
}
