package com.example.demo;

import org.springframework.http.ResponseEntity;


public interface Command <E, T> {
    // <E, T> = Entity, T is generic type in java

    ResponseEntity <T> execute(E entity);
    // we can also name the method handle.. since its a handler.. but execute is also used..

}
