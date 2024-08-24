package ProductControllerTest;

import com.example.demo.Exceptions.ProductNotValidException;
import com.example.demo.NoBsSpringBootApplication;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Product.commandHandlers.CreateProductCommandHandler;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Lecture - 17 : Unit Testing Part 1
@SpringBootTest(classes = NoBsSpringBootApplication.class)
// Tells Spring Boot its a test class
// With a param as which base class to use,
// which here tells it to use the entire project to be able to run
public class CreateProductCommandHandlerTest {
    // Q2. To test all test cases -> Run from here (class level)
    // Q3. To test all cases in multiple test classes use command -
    // mvn test (maven test basically)
    // If we see 1 extra test case that's alright that maybe, spring boot running a context load.

    @InjectMocks // Injects the thing we actually want to test in this class
    private CreateProductCommandHandler createProductCommandHandler;

    @Mock // Adds a mock productRepository, since we don't want to inject an actual one.
    // Hence we use the Mock annotation and not Autowired.
    private ProductRepository productRepository;

    @Test
    public void createProductCommandHandler_validProduct_returnsSuccess() {
        // Q5. Testing Theories.
        // Run / When / Then
        // Arrange / Act / Assert.
        // Which are both the same things.
        // Run / Arrange -> Pre-requisite / Given state
        // When / Act -> Action / what the user does. (Typically its one explicit action).
        // Then / Assert -> What happens (There can be multiple "then's" to test).


        // Given / Arrange
        // Create a product that is valid
        Product product = new Product();
        product.setId(1);
        product.setPrice(9.99);
        product.setName("Best Chocolate");
        product.setDescription("Silky Dark");
        product.setQuantity(10);
        // Q4. What could have been better here?
        // We could have used
        // builder pattern
        // or an all args constructor,
        // since we are repeating code (creation of object).

        // Or we could have a product generator class
        // (prototype and registry method -> specially useful while testing is this patterns use case)
        // which generates product then we can change the ONE or TWO.. parameter(s) we would like to change.

        // When / Act
        // Execute the method we want to test.
        ResponseEntity<String> responseEntity = createProductCommandHandler.execute(product);

        // Then / Assert
        // check if both return the same status as we want
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        //imported using - import static org.junit.jupiter.api.Assertions.assertEquals;
    }

    @Test
    public void createProductCommandHandler_invalidProduct_throwsProductNotValidException() {
        // Given / Arrange
        // Create a product that is valid
        Product product = new Product();
        product.setId(1);
        product.setPrice(-9.99); // Negative price set, to validate an invalid product test.
        product.setName("Best Chocolate");
        product.setDescription("Silky Dark");
        product.setQuantity(10);

        // When + Then / Act + Assert
        // Execute the method we want to test.
        ProductNotValidException productNotValidException =
                assertThrows(ProductNotValidException.class, () -> createProductCommandHandler.execute(product));
        // Lambda function to check if it throws the same exception or not.

        // Then we finally check if we have the same message inside it or not.
//        assertEquals("Product price can't be negative", productNotValidException.getSimpleResponse().getMessage());

        // Then 2 / Assert 2
        // Q1. We should first let the test fail, so that we know what we are testing and if the test,
        // Is basically, correct. Else, it could also be a badly written unit test which always returns true.
        // Check for the error message thrown by the exception
        // Basically we validate the exception thrown this way
        assertEquals("Product price cannot be negative or 0",
                productNotValidException.getSimpleResponse().getMessage());

    }
}
