package net.chinhung.application.component.order;

import net.chinhung.application.order.Order;

import java.util.Optional;
import java.util.stream.Stream;

public interface OrderComponent {

    Stream<Order> findOrders(GetAll query);

    Optional<Order> findById(FindById query);

    Order create(Create command);
}
