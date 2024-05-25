package com.example.demo.Product.commandHandlers;

import com.example.demo.Command;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.UpdateProductCommand;
import com.example.demo.Product.ProductRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductCommandHandler implements Command<UpdateProductCommand, ResponseEntity> {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public ResponseEntity execute(UpdateProductCommand command) {
        Product product = command.getProduct();
        // validate product
        checkValidation(product);
        product.setId(command.getId());
        // If this is not done and the entry does not already exist in the database
        // then it will work just like POST command else it will create a new entry.
        productRepository.save(product);
        return ResponseEntity.ok().build();
    }

    // This method could be abstracted even more as it is being used in multiple classes...
    // by making something like another utility class
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
