package com.example.demo.Product;

import com.example.demo.Product.Model.Product;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

// This is the 1st file we made
@RestController // we are making a controller, hence we need to specify this annotation to make it act this way
@RequestMapping("/products") // allows us to specify the endpoint
public class ProductController {
    // Create, Read, Update, Delete
    // POST,   GET,   PUT,   DELETE

    // Now we are going to use the product repository to complete our controller.

    @Autowired // allows us to inject the product repository and access it in the controller.
    private ProductRepository productRepository;
    // This allows us to access the repository in the controllers (methods) below.

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productRepository.findAll());
        // This is the easiest thing to get all the records in the table.
        // Typically in real life it isnt the case, but we are learning.
    }

    @GetMapping("/{id}") // We are specifying how to add / we are expecting the id after the end point.
    // And we have to add a parameter in the function as well, which passes the value from the end point.
    // The name/spelling of the parameter specified in both places, i.e, GetMapping and parameter should be exactly the same.

    // Why are we using Optional here? Remember in the flight management code, I read regarding optional.
    // It is the same, basically it specifies if an object is nullable what should be returned
    // Hence, if we dont apply it the return statement used wont work since it will generate the error that
    // what if the product is not found? then what should the method findById return? It should return an Optional.
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable Integer id) {
        return ResponseEntity.ok(productRepository.findById(id));
        // This will currently return null which is not a good way to handle,
        // we should send a custom exception instead, with a good specific message, which would be done later.
    }
}



// Lecture - 1,2,3
////    @GetMapping // right after this should be a plain old java method
////    public String getProduct() {
////        System.out.println("get products method");
////        return "get products endpoint";
////    }
//
//    // so usually people dont return strings
//    // however, they return a ResponseEntity instead, unlike above.
//    @GetMapping // right after this should be a plain old java method
//    public ResponseEntity getProduct() {
//        System.out.println("get products method");
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
//    }
//
//
//    // Moved our product controller in the product folder.
//
//}