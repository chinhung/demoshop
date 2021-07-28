package net.chinhung.core.component.product;

import net.chinhung.core.product.CoreProduct;

public class CreateResult {

    private final CoreProduct created;

    public CreateResult(final CoreProduct created) {
        this.created = created;
    }

    public CoreProduct getCreated() {
        return created;
    }
}
