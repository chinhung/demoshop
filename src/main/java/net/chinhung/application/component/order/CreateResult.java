package net.chinhung.application.component.order;

import net.chinhung.application.order.Order;

public class CreateResult {

    private final Order created;

    public CreateResult(final Order created) {
        this.created = created;
    }

    public Order getCreated() {
        return created;
    }
}
