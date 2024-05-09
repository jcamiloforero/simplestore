package co.jcforero.simplestore.usecase.deleteproduct;

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
class DeleteProductUseCaseTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private DeleteProductUseCase useCase;

    @Test
    void shouldDeleteProductSuccess() {
        Integer id = 123;
        Product savedProduct = Product.builder()
                .id(id)
                .price(100F)
                .description("Mi producto")
                .urlPicture("no-url.com")
                .name("Producto")
                .build();

        when(productRepository.deleteProduct(id))
                .thenReturn(Mono.just(savedProduct));

        StepVerifier.create(useCase.deleteProduct(id))
                .expectNextMatches(p -> Objects.equals(p.getId(), id))
                .expectComplete();
    }

    @Test
    void shouldDeleteProductError() {
        Integer id = 10;

        when(productRepository.deleteProduct(id))
                .thenReturn(Mono.empty());

        StepVerifier.create(useCase.deleteProduct(id))
                .expectError(RuntimeException.class);
    }
}
