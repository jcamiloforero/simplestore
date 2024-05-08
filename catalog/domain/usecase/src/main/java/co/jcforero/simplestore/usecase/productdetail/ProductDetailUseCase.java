package co.jcforero.simplestore.usecase.productdetail;

import co.jcforero.simplestore.model.product.Product;
import co.jcforero.simplestore.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProductDetailUseCase {
    private ProductRepository productRepository;
    public Mono<Product> detailProduct(Integer id) {
        return productRepository.getProductById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException())));
    }

    public Mono<Product> saveProduct(Product product) {
        return productRepository.saveProduct(product);
    }

    public Mono<Product> deleteProduct(Integer id) {
        return productRepository.deleteProduct(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException())));
    }

    public Mono<Product> updateProduct(Product product) {
        return productRepository.updateProduct(product)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException())));
    }
}
