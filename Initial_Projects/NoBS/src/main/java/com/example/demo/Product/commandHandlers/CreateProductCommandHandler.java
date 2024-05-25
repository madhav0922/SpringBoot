package com.example.demo.Product.commandHandlers;

import com.example.demo.Command;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.ProductRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

// Lecture 9 : Command Handler
@Service
public class CreateProductCommandHandler implements Command<Product, String> {

    @Autowired // allows us to inject the product repository and access it in the command handler.
    private ProductRepository productRepository;
    @Override
    public ResponseEntity<String> execute(Product product) {
        // Validate then save...
        checkValidation(product);

        // Validation provides the safety for our app here...
        // The validation won't allow to save a wrong product and will throw an exception and stop execution.

        productRepository.save(product);
        return ResponseEntity.ok().build();
    }

    private void checkValidation(Product product) {
        // name
        if (StringUtils.isBlank(product.getName())) { // micrometer utils... check import
            throw new RuntimeException("Product name cannot be empty");
        }
        // description
        if(StringUtils.isBlank(product.getDescription())) {
            throw new RuntimeException("Description cannot be empty");
        }
        // price
        if(product.getPrice() <= 0.0) {
            throw new RuntimeException("Price cannot be negative or 0");
        }
        // quantity
        if(product.getQuantity() <= 0) {
            throw new RuntimeException("Quantity cannot be negative or 0");
        }
    }
}
