package com.example.demo.Product.queryHandlers;

import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.ProductDTO;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

// Lecture - 7 : Query Handler 1
@Service // This tell Spring Boot that this is our business logic.
// Now we are implementing our interface.
// This does not require an input hence the input is Void.
// Lecture 13 : DTOs
// We are changing return type from Product to ProductDTO
public class GetAllProductsQueryHandler implements Query<Void, List<ProductDTO>> {

    @Autowired // allows us to inject the product repository and access it in the query Handler.
    private ProductRepository productRepository;

    // Lecture 13 : DTOs
    // We are changing return type from Product to ProductDTO
    @Override
    public ResponseEntity<List<ProductDTO>> execute(Void input) {
        List<ProductDTO> productDTOs = productRepository
                .findAll()
                .stream()
                .map(ProductDTO::new)
                .toList();
        return ResponseEntity.ok(productDTOs);
        // Lecture 15: Exception Handling - Part 2 - Custom Exception
        // Since this is returning a list of products and not a single product
        // We are not returning any exception here since it'd be returning an empty list []
        // in case no product(s) exist in the repository.
    }
}
