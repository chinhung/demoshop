package net.chinhung.application.endpoint.order;

import java.util.List;

public interface OrderEndpoint {

    List<OrderDTO> findOrders();

    OrderDTO findById(String id);

    OrderDTO createOrder(CreateOrder command);
}
