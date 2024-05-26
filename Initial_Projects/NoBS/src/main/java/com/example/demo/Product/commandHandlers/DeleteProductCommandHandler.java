package com.example.demo.Product.commandHandlers;

import com.example.demo.Command;
import com.example.demo.Exceptions.ProductNotFoundException;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteProductCommandHandler implements Command<Integer, ResponseEntity> {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public ResponseEntity<ResponseEntity> execute(Integer id) {
        // Product product = productRepository.getReferenceById(id);
        // or Product product = productRepository.findById(id).get();

        // Lecture 15: Exception Handling - Part 2 - Custom Exception
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()) {
            throw new ProductNotFoundException();
        }
        productRepository.delete(optionalProduct.get());
        return ResponseEntity.ok().build();
    }
}
