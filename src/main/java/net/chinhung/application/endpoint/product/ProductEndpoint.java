package net.chinhung.application.endpoint.product;

import java.util.List;

public interface ProductEndpoint {

    List<ProductDTO> findProducts();

    ProductDTO createProduct(CreateProduct param);
}
