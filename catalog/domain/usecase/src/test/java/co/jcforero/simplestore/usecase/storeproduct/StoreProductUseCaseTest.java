package co.jcforero.simplestore.usecase.storeproduct;

import co.jcforero.simplestore.model.product.Product;
import co.jcforero.simplestore.model.product.gateways.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StoreProductUseCaseTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private StoreProductUseCase useCase;

    @Test
    void shouldCreateProductSuccess() {
        Product product = Product.builder()
                .price(100F)
                .description("Mi producto")
                .urlPicture("no-url.com")
                .name("Producto")
                .build();

        Product savedProduct = Product.builder()
                .id(123)
                .price(100F)
                .description("Mi producto")
                .urlPicture("no-url.com")
                .name("Producto")
                .build();

        when(productRepository.saveProduct(product))
                .thenReturn(Mono.just(savedProduct));

        StepVerifier.create(useCase.saveProduct(product))
                .expectNextMatches(p -> Objects.equals(p.getId(), 123))
                .expectComplete();
    }
}
