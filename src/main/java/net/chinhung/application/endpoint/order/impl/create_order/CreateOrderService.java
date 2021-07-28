package net.chinhung.application.endpoint.order.impl.create_order;

import net.chinhung.application.endpoint.order.impl.OrderConverter;
import net.chinhung.application.endpoint.order.CreateOrder;
import net.chinhung.application.endpoint.order.OrderDTO;
import net.chinhung.application.order.Item;
import net.chinhung.application.order.Order;
import net.chinhung.application.component.order.OrderComponent;
import net.chinhung.application.component.order.Create;
import net.chinhung.core.order.CoreItem;
import net.chinhung.fundamental.aspect.CommandService;

import java.util.List;
import java.util.stream.Collectors;

public class CreateOrderService implements CommandService<CreateOrder, OrderDTO> {

    private final OrderComponent orderComponent;
    private final OrderConverter orderConverter;

    public CreateOrderService(
            final OrderComponent orderComponent,
            final OrderConverter orderConverter
    ) {
        this.orderComponent = orderComponent;
        this.orderConverter = orderConverter;
    }

    @Override
    public OrderDTO execute(final CreateOrder createOrder) {
        List<Item> items = createOrder.getOrderLines().stream().map(line -> {
            Item item = new Item(new CoreItem(line.getName(), line.getProductId(), line.getQuantity()));
            return item;
        }).collect(Collectors.toList());
        Order order = orderComponent.create(new Create(items));
        return orderConverter.toOrderDTO(order);
    }
}
