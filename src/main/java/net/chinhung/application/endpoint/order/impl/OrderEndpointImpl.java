package net.chinhung.application.endpoint.order.impl;

import net.chinhung.application.endpoint.order.OrderDTO;
import net.chinhung.application.endpoint.order.OrderEndpoint;
import net.chinhung.application.endpoint.order.CreateOrder;
import net.chinhung.application.endpoint.order.impl.find_by_id.FindById;
import net.chinhung.application.endpoint.order.impl.find_orders.FindOrders;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;

import java.util.List;

public class OrderEndpointImpl implements OrderEndpoint {

    private final QueryService<FindOrders, List<OrderDTO>> findOrders;
    private final QueryService<FindById, OrderDTO> findById;
    private final CommandService<CreateOrder, OrderDTO> createOrder;

    public OrderEndpointImpl(
            final QueryService<FindOrders, List<OrderDTO>> findOrders,
            final QueryService<FindById, OrderDTO> findById,
            final CommandService<CreateOrder, OrderDTO> createOrder
    ) {
        this.findOrders = findOrders;
        this.findById = findById;
        this.createOrder = createOrder;
    }

    @Override
    public List<OrderDTO> findOrders() {
        return findOrders.execute(new FindOrders());
    }

    @Override
    public OrderDTO findById(String id) {
        return findById.execute(new FindById(id));
    }

    @Override
    public OrderDTO createOrder(CreateOrder command) {
        return createOrder.execute(command);
    }
}
