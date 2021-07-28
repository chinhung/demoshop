package net.chinhung.application.component.order;

import net.chinhung.application.order.Item;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

public class Create {

    @Size(min = 1)
    private final List<@Valid Item> items;

    public Create(final List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }
}
