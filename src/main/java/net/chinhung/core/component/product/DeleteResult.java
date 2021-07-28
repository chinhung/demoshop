package net.chinhung.core.component.product;

import net.chinhung.core.product.CoreProduct;

public class DeleteResult {

    private final CoreProduct deleted;

    public DeleteResult(final CoreProduct deleted) {
        this.deleted = deleted;
    }

    public CoreProduct getDeleted() {
        return deleted;
    }
}
