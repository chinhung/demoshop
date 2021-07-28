package net.chinhung.application.component.inventory;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class InventoryQuery {

    @NotEmpty
    private final String productId;

    @NotEmpty
    private final String productName;

    @NotNull
    @Positive
    private final Integer quantity;

    public InventoryQuery(
            final String productId,
            final String productName,
            final Integer quantity
    ) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }
}
