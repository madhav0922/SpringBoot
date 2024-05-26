package com.example.demo.Product;

import com.example.demo.Exceptions.ProductNotFoundException;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.ProductDTO;
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

    // Lecture 12 : Dependency Injection and @Autowired explained.
    @Autowired // allows us to inject the product repository and access it in the controller.
    // basically spring boot goes and finds the product repository in the class (Java Bean basically),
    // brings it to this class and injects it. This is known as a dependency injection.
    // Specifically it is Field Injection, which is the specific way in which we are injecting it.
    // But in order for this to work the class that is being used here which is ProductRepository lets say...
    // should be annotated with @Repository, @Service, @Component, etc.
    private ProductRepository productRepository;
//    // This allows us to access the repository in the controllers (methods) below.
//
//    // Lecture - 7
    @Autowired private GetAllProductsQueryHandler getAllProductsQueryHandler;
    @Autowired private GetProductQueryHandler getProductQueryHandler;
    @Autowired private CreateProductCommandHandler createProductCommandHandler;
    @Autowired private UpdateProductCommandHandler updateProductCommandHandler;
    @Autowired private DeleteProductCommandHandler deleteProductCommandHandler;


    // The above written comment was field injection and another way to do this is a
    // Constructor Injection
    // It has its own advantages / disadvantages... but clearly the code to write is more.
    // One potential consequence could be on the testing side. Would be discussed later.
//    private final ProductRepository productRepository;
//    private final GetAllProductsQueryHandler getAllProductsQueryHandler;
//    private final GetProductQueryHandler getProductQueryHandler;
//    private final CreateProductCommandHandler createProductCommandHandler;
//    private final UpdateProductCommandHandler updateProductCommandHandler;
//    private final DeleteProductCommandHandler deleteProductCommandHandler;
//    @Autowired
//    public ProductController (
//            ProductRepository productRepository,
//            GetAllProductsQueryHandler getAllProductsQueryHandler,
//            GetProductQueryHandler getProductQueryHandler,
//            CreateProductCommandHandler createProductCommandHandler,
//            UpdateProductCommandHandler updateProductCommandHandler,
//            DeleteProductCommandHandler deleteProductCommandHandler
//    ) {
//        this.productRepository = productRepository;
//        this.getAllProductsQueryHandler = getAllProductsQueryHandler;
//        this.getProductQueryHandler = getProductQueryHandler;
//        this.createProductCommandHandler = createProductCommandHandler;
//        this.updateProductCommandHandler = updateProductCommandHandler;
//        this.deleteProductCommandHandler = deleteProductCommandHandler;
//    }

    // Lecture 13 : DTOs
    // We are changing return type from Product to ProductDTO
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts() {
        // Lecture - 7
        return getAllProductsQueryHandler.execute(null);
    }

    // Lecture - 8 : Query Handler 2
    // Now we are better accounting it and refactoring it for Optional.
    // So basically we have removed optional here, see above commented code.
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id) {
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

    // Lecture 15: Exception Handling - Part 2 - Custom Exception at controller level
    // moved to GlobalExceptionHandler in Lecture 16
//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<String> handleProductNotFoundException() {
//        return ResponseEntity.status(404).body("Product not found.");
//    }

}
