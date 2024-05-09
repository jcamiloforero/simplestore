package co.jcforero.simplestore.usecase.productdetail;

import co.jcforero.simplestore.model.product.Product;
import co.jcforero.simplestore.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProductDetailUseCase {
    private final ProductRepository productRepository;

    public Mono<Product> detailProduct(Integer id) {
        return productRepository.getProductById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException())));
    }
}
