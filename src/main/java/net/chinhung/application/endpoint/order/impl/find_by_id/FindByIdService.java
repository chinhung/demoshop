package net.chinhung.application.endpoint.order.impl.find_by_id;

import net.chinhung.application.endpoint.order.impl.OrderConverter;
import net.chinhung.application.endpoint.order.OrderDTO;
import net.chinhung.application.order.Order;
import net.chinhung.application.component.order.OrderComponent;
import net.chinhung.fundamental.aspect.QueryService;

import java.util.Optional;

public class FindByIdService implements QueryService<FindById, OrderDTO> {

    private final OrderComponent orderComponent;
    private final OrderConverter orderConverter;

    public FindByIdService(
            final OrderComponent orderComponent,
            final OrderConverter orderConverter
    ) {
        this.orderComponent = orderComponent;
        this.orderConverter = orderConverter;
    }

    @Override
    public OrderDTO execute(final FindById findById) {
        final Optional<Order> order = orderComponent.findById(new net.chinhung.application.component.order.FindById(findById.getId()));
        if (order.isPresent()) {
            return orderConverter.toOrderDTO(order.get());
        }
        throw new RuntimeException("order not found");
    }
}
