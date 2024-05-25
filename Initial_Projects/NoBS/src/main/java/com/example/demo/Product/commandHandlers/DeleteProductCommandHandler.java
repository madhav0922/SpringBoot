package com.example.demo.Product.commandHandlers;

import com.example.demo.Command;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductCommandHandler implements Command<Integer, ResponseEntity> {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public ResponseEntity<ResponseEntity> execute(Integer id) {
        Product product = productRepository.getReferenceById(id);
        productRepository.delete(product);
        return ResponseEntity.ok().build();
    }
}
