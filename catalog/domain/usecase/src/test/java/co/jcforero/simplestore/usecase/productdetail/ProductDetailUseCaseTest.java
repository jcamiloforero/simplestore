package co.jcforero.simplestore.usecase.productdetail;

import co.jcforero.simplestore.model.product.Product;
import co.jcforero.simplestore.model.product.gateways.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductDetailUseCaseTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductDetailUseCase useCase;

    @Test
    void shouldReturnProductCorrectly() {
        Integer id = 10;

        Product product = Product.builder()
                .id(10)
                .name("Hoja")
                .description("Una hoja en blanco")
                .price(1000F)
                .build();
        when(productRepository.getProductById(id))
                .thenReturn(Mono.just(product));

        StepVerifier.create(useCase.detailProduct(id))
                .expectNextMatches(p -> Objects.equals(p.getId(), id))
                .expectComplete();
    }

    @Test
    void shouldReturnProductNotFound() {
        Integer id = 10;

        when(productRepository.getProductById(id))
                .thenReturn(Mono.empty());

        StepVerifier.create(useCase.detailProduct(id))
                .expectError(RuntimeException.class);

        //Assertions.assertThrows(RuntimeException.class, () -> useCase.detailProduct(id));

    }

}
