package net.chinhung.core.component.inventory;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class Create {

    @NotEmpty
    private final String name;

    @NotEmpty
    private final String productId;

    @NotNull
    @PositiveOrZero
    private final Integer stockQuantity;

    public Create(final String name, final String productId, final Integer stockQuantity) {
        this.name = name;
        this.productId = productId;
        this.stockQuantity = stockQuantity;
    }

    public String getName() {
        return name;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }
}
