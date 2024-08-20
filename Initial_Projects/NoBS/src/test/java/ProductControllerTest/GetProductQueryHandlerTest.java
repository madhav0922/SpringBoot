package ProductControllerTest;

import com.example.demo.Exceptions.ProductNotFoundException;
import com.example.demo.NoBsSpringBootApplication;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.ProductDTO;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Product.queryHandlers.GetProductQueryHandler;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = NoBsSpringBootApplication.class)
public class GetProductQueryHandlerTest {

    @InjectMocks
    private GetProductQueryHandler getProductQueryHandler;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void getProductQueryHandler_validId_returnsProductDTO() {
        // We return a DTO here, which is the same thing as Product with a few fields removed.

        // Given / Arrange
        // Create a product that is valid
        Product product = new Product();
        product.setId(1);
        product.setPrice(9.99);
        product.setName("Best Chocolate");
        product.setDescription("Silky Dark");
        product.setQuantity(10);

        // Given
        ProductDTO expectedDTO =  new ProductDTO(product);

        // When -> We are trying to get the actual ProductDTO here.
        /*
            ResponseEntity<ProductDTO> actualResponse =  getProductQueryHandler.execute(product.getId());
         */
        // This will fail though, because we are not mocking the database,
        // we are actually retrieve an object instead
        // We did not tell our repository how to respond
        // This is a mock product repository and we don't want it to actually touch the database.
        // All we want to do is to ensure that the methods we wrote were correct.
        // Hence, we need to mock the response to ensure our method is working properly.

        // CustomBaseException(status=400 BAD_REQUEST, simpleResponse=SimpleResponse(message=Product Not Found.))
        //	at com.example.demo.Product.queryHandlers.GetProductQueryHandler.execute(GetProductQueryHandler.java:27)
        //	at ProductControllerTest.GetProductQueryHandlerTest.getProductQueryHandler_validId_returnsProductDTO(GetProductQueryHandlerTest.java:40)
        //	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
        //	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        //	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)


        /*
            Hence, to solve this issue ->
         */
        // We need to mock the response to ensure our method is working properly.
        // Given / And (In other testing languages, basically we can have multiple givens as well)
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        // When
        ResponseEntity<ProductDTO> actualResponse =  getProductQueryHandler.execute(product.getId());
        // So when the product repository is pinged and it gets a findById,
        // then return the optional of our product. (optional could be null/empty as well as we learnt)

        // What this line basically is doing ->
        // "Stubbing" the repository that hey if you get pinged in this mock environment
        // go ahead and return this product (our mock created product) and then we can verify everything else.

        /*
         *  Stubbing -> API stubbing is a technique used in software development and testing,
         *          where a dummy or placeholder implementation of API responses is used as a
         *          temporary substitute for the actual API.
         */

        // Then
        // Check if the responses are same or not using body method.
        assertEquals(expectedDTO, actualResponse.getBody());
        // Check for HTTP Status
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    // This test is a mix of both the lectures.
    @Test
    public void getProductQueryHandler_invalidId_throwsProductNotFoundException() {
        // When -> We are directly validating for id 1 here.
//        when(productRepository.findById(1)).thenReturn(Optional.empty());
        // When productRepository gets pinged, Return an empty optional.

        // When + Then
        // Check the exception using assertThrows
        ProductNotFoundException productNotFoundException =
                assertThrows(ProductNotFoundException.class, () -> getProductQueryHandler.execute(1));

        // Then
        // Check for the error message thrown by the exception
        // Basically we validate the exception thrown this way
        assertEquals("Product Not Found.",
                productNotFoundException.getSimpleResponse().getMessage());
    }
}
