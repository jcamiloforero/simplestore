package co.jcforero.simplestore.usecase.modifyproduct;

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
class ModifyProductUseCaseTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ModifyProductUseCase useCase;

    @Test
    void shouldUpdateProductSuccess() {
        Product product = Product.builder()
                .id(123)
                .price(100F)
                .description("Mi producto")
                .urlPicture("no-url.com")
                .name("Producto")
                .build();

        when(productRepository.updateProduct(product))
                .thenReturn(Mono.just(product));

        StepVerifier.create(useCase.updateProduct(product))
                .expectNextMatches(p -> Objects.equals(p.getPrice(), product.getPrice()))
                .expectComplete();
    }

    @Test
    void shouldUpdateProductErrorProductNotFound() {
        Product product = Product.builder()
                .id(123)
                .price(100F)
                .description("Mi producto")
                .urlPicture("no-url.com")
                .name("Producto")
                .build();

        when(productRepository.updateProduct(product))
                .thenReturn(Mono.empty());

        StepVerifier.create(useCase.updateProduct(product))
                .expectError(RuntimeException.class);
    }
}
