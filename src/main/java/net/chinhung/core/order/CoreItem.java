package net.chinhung.core.order;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class CoreItem {

    @NotEmpty
    private final String name;

    @NotEmpty
    private final String productId;

    @NotNull
    @PositiveOrZero
    private final Integer quantity;

    public CoreItem(final String name, final String productId, final Integer quantity) {
        this.name = name;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
