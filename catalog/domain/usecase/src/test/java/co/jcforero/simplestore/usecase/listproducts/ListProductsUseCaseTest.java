package co.jcforero.simplestore.usecase.listproducts;

import co.jcforero.simplestore.model.product.Product;
import co.jcforero.simplestore.model.product.gateways.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListProductsUseCaseTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ListProductsUseCase useCase;

    @Test
    void shouldGetListProductsSuccess() {

        Flux<Product> products = Flux.just(
                Product.builder().id(123).build(),
                Product.builder().id(456).build());

        when(productRepository.getProducts())
                .thenReturn(products);

        StepVerifier.create(useCase.getAllProducts())
                .expectNextMatches(p -> Objects.equals(p.getId(), 123))
                .expectNextMatches(p -> Objects.equals(p.getId(), 456))
                .expectComplete();
    }
}
