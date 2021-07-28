package net.chinhung.application.order;

import net.chinhung.core.order.CoreOrder;

import java.util.List;
import java.util.stream.Collectors;

public class Order {

    private final CoreOrder coreOrder;

    public Order(CoreOrder coreOrder) {
        this.coreOrder = coreOrder;
    }

    public String getId() {
        return coreOrder.getId();
    }

    public List<Item> getItems() {
        return coreOrder.getItems().stream().map(Item::new).collect(Collectors.toList());
    }

    public Status getStatus() {
        return Status.from(coreOrder.getStatus());
    }
}
