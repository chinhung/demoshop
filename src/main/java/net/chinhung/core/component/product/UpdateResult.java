package net.chinhung.core.component.product;

import net.chinhung.core.product.CoreProduct;

public class UpdateResult {

    private final CoreProduct updated;

    public UpdateResult(final CoreProduct updated) {
        this.updated = updated;
    }

    public CoreProduct getUpdated() {
        return updated;
    }
}
