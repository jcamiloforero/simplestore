package co.jcforero.simplestore.model.product.gateways;

import co.jcforero.simplestore.model.product.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Mono<Product> saveProduct(Product product);
    Flux<Product> getProducts();
    Mono<Product> getProductById(Integer id);
    Mono<Product> updateProduct(Product product);
    Mono<Product> deleteProduct(Integer id);
}
