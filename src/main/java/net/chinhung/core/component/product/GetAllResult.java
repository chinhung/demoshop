package net.chinhung.core.component.product;

import net.chinhung.core.product.CoreProduct;

import java.util.List;
import java.util.stream.Stream;

public class GetAllResult {

    private final List<CoreProduct> founded;

    public GetAllResult(final List<CoreProduct> founded) {
        this.founded = founded;
    }

    public Stream<CoreProduct> getFounded() {
        return founded.stream();
    }
}
