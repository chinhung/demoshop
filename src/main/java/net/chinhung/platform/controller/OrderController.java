package net.chinhung.platform.controller;

import net.chinhung.application.endpoint.order.OrderDTO;
import net.chinhung.application.endpoint.order.OrderEndpoint;
import net.chinhung.application.endpoint.order.CreateOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/apple/order")
public class OrderController {

    private final OrderEndpoint orderEndpoint;

    @Autowired
    public OrderController(OrderEndpoint orderEndpoint) {
        this.orderEndpoint = orderEndpoint;
    }

    @GetMapping("/orders")
    public List<OrderDTO> findOrders() {
        return orderEndpoint.findOrders();
    }

    @GetMapping("/order/{id}")
    public OrderDTO findById(@PathVariable String id) {
        return orderEndpoint.findById(id);
    }

    @PostMapping("/order")
    public OrderDTO createOrder(@RequestBody CreateOrder command) {
        return orderEndpoint.createOrder(command);
    }
}
