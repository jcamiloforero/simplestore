package co.jcforero.simplestore.usecase.deleteproduct;

import co.jcforero.simplestore.model.product.Product;
import co.jcforero.simplestore.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DeleteProductUseCase {
    private final ProductRepository productRepository;

    public Mono<Product> deleteProduct(Integer id) {
        return productRepository.deleteProduct(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException())));
    }
}
