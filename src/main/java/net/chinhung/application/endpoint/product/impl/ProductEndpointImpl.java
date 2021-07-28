package net.chinhung.application.endpoint.product.impl;

import net.chinhung.application.endpoint.product.ProductDTO;
import net.chinhung.application.endpoint.product.ProductEndpoint;
import net.chinhung.application.endpoint.product.CreateProduct;
import net.chinhung.application.endpoint.product.impl.find_products.FindProducts;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;

import java.util.List;

public class ProductEndpointImpl implements ProductEndpoint {

    private final QueryService<FindProducts, List<ProductDTO>> findProducts;
    private final CommandService<CreateProduct, ProductDTO> createProduct;

    public ProductEndpointImpl(
            final QueryService<FindProducts, List<ProductDTO>> findProducts,
            final CommandService<CreateProduct, ProductDTO> createProduct
    ) {
        this.findProducts = findProducts;
        this.createProduct = createProduct;
    }

    public List<ProductDTO> findProducts() {
        return findProducts.execute(new FindProducts());
    }

    public ProductDTO createProduct(CreateProduct command) {
        return createProduct.execute(command);
    }
}
