package com.example.demo;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

// Lecture - 7
// At the same level of spring boot app we have created an interface.
// This takes I input and produces O output.
public interface Query <I, O> {
    ResponseEntity<O> execute(I input);
}
