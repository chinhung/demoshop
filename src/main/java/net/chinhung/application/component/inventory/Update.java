package net.chinhung.application.component.inventory;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class Update {

    @NotEmpty
    private final String productId;

    @NotNull
    @PositiveOrZero
    private final Integer quantity;

    public Update(final String productId, final Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
