package net.chinhung.application.endpoint.order.impl.find_orders;

import net.chinhung.application.endpoint.order.impl.OrderConverter;
import net.chinhung.application.endpoint.order.OrderDTO;
import net.chinhung.application.order.Order;
import net.chinhung.application.component.order.OrderComponent;
import net.chinhung.application.component.order.GetAll;
import net.chinhung.fundamental.aspect.QueryService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindOrdersService implements QueryService<FindOrders, List<OrderDTO>> {

    private final OrderComponent orderComponent;
    private final OrderConverter orderConverter;

    public FindOrdersService(
            final OrderComponent orderComponent,
            final OrderConverter orderConverter
    ) {
        this.orderComponent = orderComponent;
        this.orderConverter = orderConverter;
    }

    @Override
    public List<OrderDTO> execute(final FindOrders findOrders) {
        Stream<Order> orders = orderComponent.findOrders(new GetAll());
        return orderConverter.toOrderDTO(orders).collect(Collectors.toList());
    }
}
