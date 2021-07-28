package net.chinhung.application.component.product;

import net.chinhung.application.product.Product;

public class CreateResult {

    private final Product created;

    public CreateResult(final Product created) {
        this.created = created;
    }

    public Product getCreated() {
        return created;
    }
}
