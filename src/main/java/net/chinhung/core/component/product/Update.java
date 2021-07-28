package net.chinhung.core.component.product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class Update {

    @NotEmpty
    private final String id;

    @NotEmpty
    private final String name;

    @NotNull
    @PositiveOrZero
    private final Integer price;

    public Update(final String id, final String name, final Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}
