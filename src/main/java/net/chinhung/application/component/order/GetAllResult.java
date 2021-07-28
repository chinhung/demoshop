package net.chinhung.application.component.order;

import net.chinhung.application.order.Order;

import java.util.List;
import java.util.stream.Stream;

public class GetAllResult {

    private final List<Order> founded;

    public GetAllResult(final List<Order> founded) {
        this.founded = founded;
    }

    public Stream<Order> getFounded() {
        return founded.stream();
    }
}
