package net.chinhung.application.endpoint.product.impl;


import net.chinhung.application.endpoint.product.ProductDTO;
import net.chinhung.application.product.Product;

import java.util.function.Function;
import java.util.stream.Stream;

public class ProductConverter {

    private final Function<Product, ProductDTO> fromProductToProductDTO = product -> {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity().getValue());
        return dto;
    };

    public Stream<ProductDTO> toProductDTO(Stream<Product> products) {
        return products.map(fromProductToProductDTO);
    }

    public ProductDTO toProductDTO(Product product) {
        return fromProductToProductDTO.apply(product);
    }
}
