package net.chinhung.application.component.endpoint.impl.order_endpoint;

import net.chinhung.application.endpoint.order.CreateOrder;
import net.chinhung.application.endpoint.order.impl.OrderEndpointImpl;
import net.chinhung.application.endpoint.order.impl.find_by_id.FindById;
import net.chinhung.application.endpoint.order.impl.find_orders.FindOrders;
import net.chinhung.application.endpoint.order.OrderDTO;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class OrderEndpointImplTest {

    @Mock private QueryService<FindOrders, List<OrderDTO>> findOrders;
    @Mock private QueryService<FindById, OrderDTO> findById;
    @Mock private CommandService<CreateOrder, OrderDTO> createOrder;

    private OrderEndpointImpl instance;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        instance = new OrderEndpointImpl(findOrders, findById, createOrder);
    }

    @Test
    public void findOrders() {
        instance.findOrders();

        Mockito.verify(findOrders).execute(Mockito.any());
    }

    @Test
    public void findById() {
        instance.findById(Mockito.anyString());

        Mockito.verify(findById).execute(Mockito.any());
    }

    @Test
    public void createOrder() {
        instance.createOrder(Mockito.any());

        Mockito.verify(createOrder).execute(Mockito.any());
    }
}
