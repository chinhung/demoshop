package net.chinhung.application.component.product;

import net.chinhung.application.product.Product;

import java.util.stream.Stream;

public interface ProductComponent {

    Stream<Product> findProducts();

    Product createProduct(String name, Integer price);
}
