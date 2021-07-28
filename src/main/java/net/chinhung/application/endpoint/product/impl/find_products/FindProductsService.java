package net.chinhung.application.endpoint.product.impl.find_products;

import net.chinhung.application.endpoint.product.impl.ProductConverter;
import net.chinhung.application.endpoint.product.ProductDTO;
import net.chinhung.application.product.Product;
import net.chinhung.application.component.product.ProductComponent;
import net.chinhung.fundamental.aspect.QueryService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindProductsService implements QueryService<FindProducts, List<ProductDTO>> {

    private final ProductComponent productComponent;
    private final ProductConverter productConverter;

    public FindProductsService(
            final ProductComponent productComponent,
            final ProductConverter productConverter
    ) {
        this.productComponent = productComponent;
        this.productConverter = productConverter;
    }

    @Override
    public List<ProductDTO> execute(FindProducts findProducts) {
        Stream<Product> products = productComponent.findProducts();
        return productConverter.toProductDTO(products).collect(Collectors.toList());
    }
}
