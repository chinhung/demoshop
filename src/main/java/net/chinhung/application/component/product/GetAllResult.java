package net.chinhung.application.component.product;

import net.chinhung.application.product.Product;

import java.util.List;
import java.util.stream.Stream;

public class GetAllResult {

    private final List<Product> founded;

    public GetAllResult(final List<Product> founded) {
        this.founded = founded;
    }

    public Stream<Product> getFounded() {
        return founded.stream();
    }
}
