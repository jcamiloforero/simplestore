package co.jcforero.simplestore.usecase.listproducts;

import co.jcforero.simplestore.model.product.Product;
import co.jcforero.simplestore.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ListProductsUseCase {
    private final ProductRepository productRepository;

    public Flux<Product> getAllProducts() {
        return productRepository.getProducts();
    }
}
