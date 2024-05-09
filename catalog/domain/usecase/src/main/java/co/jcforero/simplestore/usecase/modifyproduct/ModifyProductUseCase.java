package co.jcforero.simplestore.usecase.modifyproduct;

import co.jcforero.simplestore.model.product.Product;
import co.jcforero.simplestore.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ModifyProductUseCase {
    private final ProductRepository productRepository;

    public Mono<Product> updateProduct(Product product) {
        return productRepository.updateProduct(product)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException())));
    }
}
