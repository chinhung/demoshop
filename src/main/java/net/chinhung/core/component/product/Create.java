package net.chinhung.core.component.product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class Create {

    @NotEmpty
    private final String name;

    @NotNull
    @PositiveOrZero
    private final Integer price;

    public Create(final String name, final Integer price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}
