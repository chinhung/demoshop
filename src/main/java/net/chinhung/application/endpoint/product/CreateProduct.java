package net.chinhung.application.endpoint.product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

public class CreateProduct {

    @NotEmpty
    private String name;

    @NotEmpty
    @PositiveOrZero
    private String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
