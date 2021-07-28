package net.chinhung.application.component.order;

import net.chinhung.application.order.Order;

import java.util.Optional;

public class FindByIdResult {

    private final Optional<Order> founded;

    public FindByIdResult(final Optional<Order> founded) {
        this.founded = founded;
    }

    public Optional<Order> getFounded() {
        return founded;
    }
}
