package net.chinhung.application.endpoint.order;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class OrderLine {

    @NotEmpty
    private String name;

    @NotEmpty
    private String productId;

    @NotNull
    @Positive
    private Integer quantity;

    public OrderLine() {
    }

    public OrderLine(final String name, final String productId, final Integer quantity) {
        this.name = name;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
