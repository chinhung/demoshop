package net.chinhung.core.component.inventory;

import javax.validation.constraints.NotEmpty;

public class FindByProductId {

    @NotEmpty
    private final String productId;

    public FindByProductId(final String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }
}
