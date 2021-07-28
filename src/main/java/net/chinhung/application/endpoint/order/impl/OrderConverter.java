package net.chinhung.application.endpoint.order.impl;


import net.chinhung.application.endpoint.order.OrderDTO;
import net.chinhung.application.endpoint.order.OrderLineDTO;
import net.chinhung.application.order.Order;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderConverter {

    private final Function<Order, OrderDTO> fromOrderToOrderDTO = order -> {
        final OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderLines(order.getItems().stream().map(item -> {
            OrderLineDTO orderLineDTO = new OrderLineDTO();
            orderLineDTO.setName(item.getName());
            orderLineDTO.setProductId(item.getProductId());
            orderLineDTO.setQuantity(item.getQuantity());
            return orderLineDTO;
        }).collect(Collectors.toList()));
        dto.setStatus(order.getStatus().toString());
        return dto;
    };

    public Stream<OrderDTO> toOrderDTO(final Stream<Order> orders) {
        return orders.map(fromOrderToOrderDTO);
    }

    public OrderDTO toOrderDTO(final Order order) {
        return fromOrderToOrderDTO.apply(order);
    }
}
