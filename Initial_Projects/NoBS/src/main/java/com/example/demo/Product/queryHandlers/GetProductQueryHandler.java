package com.example.demo.Product.queryHandlers;

import com.example.demo.Exceptions.ProductNotFoundException;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.ProductDTO;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Lecture - 8 : Query Handler 2
@Service
public class GetProductQueryHandler implements Query<Integer, ProductDTO> {

    @Autowired // allows us to inject the product repository and access it in the query Handler.
    private ProductRepository productRepository;
    @Override
    public ResponseEntity<ProductDTO> execute(Integer id) {
        Optional<Product> product = productRepository.findById(id); // This basically says the same thing, if we found it then okay otherwise we'll throw an exception.
        if(product.isEmpty()) {
            // Here we throw the exception (generic for now)
            // throw new RuntimeException("Product not found.");
            // Lecture 15: Exception Handling - Part 2 - Custom Exception
            throw new ProductNotFoundException();
        } else {
            // Lecture 13 - DTOs - Changed from returning a Product to a ProductDTO
            return ResponseEntity.ok(new ProductDTO(product.get()));
        }
    }
}
