package co.jcforero.simplestore.usecase.storeproduct;

import co.jcforero.simplestore.model.product.Product;
import co.jcforero.simplestore.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class StoreProductUseCase {
    private final ProductRepository productRepository;

    public Mono<Product> saveProduct(Product product) {
        return productRepository.saveProduct(product);
    }
}
