package com.example.demo.Product;

import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.UpdateProductCommand;
import com.example.demo.Product.commandHandlers.CreateProductCommandHandler;
import com.example.demo.Product.commandHandlers.DeleteProductCommandHandler;
import com.example.demo.Product.commandHandlers.UpdateProductCommandHandler;
import com.example.demo.Product.queryHandlers.GetAllProductsQueryHandler;
import com.example.demo.Product.queryHandlers.GetProductQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This is the 1st file we made
@RestController // we are making a controller, hence we need to specify this annotation to make it act this way
@RequestMapping("/products") // allows us to specify the endpoint
public class ProductController {
    // Create, Read, Update, Delete
    // POST, GET, PUT, DELETE

    @Autowired // allows us to inject the product repository and access it in the controller.
    private ProductRepository productRepository;
    // This allows us to access the repository in the controllers (methods) below.

    // Lecture - 7
    @Autowired private GetAllProductsQueryHandler getAllProductsQueryHandler;
    @Autowired private GetProductQueryHandler getProductQueryHandler;
    @Autowired private CreateProductCommandHandler createProductCommandHandler;
    @Autowired private UpdateProductCommandHandler updateProductCommandHandler;
    @Autowired private DeleteProductCommandHandler deleteProductCommandHandler;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        // Lecture - 7
        return getAllProductsQueryHandler.execute(null);
    }

    // Lecture - 8 : Query Handler 2
    // Now we are better accounting it and refactoring it for Optional.
    // So basically we have removed optional here, see above commented code.
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id) {
        return getProductQueryHandler.execute(id);
    }

    // Lecture 9 : Command Handler
    @PostMapping
    // @RequestBody will tell that in the HTTP request body look for a product.
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        return createProductCommandHandler.execute(product);
    }

    // Lecture 10 : Command Handler 2
    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        UpdateProductCommand updateProductCommand = new UpdateProductCommand(id, product);
        return updateProductCommandHandler.execute(updateProductCommand);
    }

    // Lecture 11 : Command Handler 3
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        // Not fully done yet as we have not handled the Optional (nullable) yet... in case we don't find the product with the given id.
        // It'd be done in further classes with custom exception...
        return deleteProductCommandHandler.execute(id);
    }
}
