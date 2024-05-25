package com.example.demo.Product.queryHandlers;

import com.example.demo.Product.Model.Product;
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
public class GetAllProductsQueryHandler implements Query<Void, List<Product>> {

    @Autowired // allows us to inject the product repository and access it in the query Handler.
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<List<Product>> execute(Void input) {
        return ResponseEntity.ok(productRepository.findAll());
    }
}
