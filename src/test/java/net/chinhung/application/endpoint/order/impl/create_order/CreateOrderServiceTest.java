package net.chinhung.application.endpoint.order.impl.create_order;

import net.chinhung.application.endpoint.order.impl.OrderConverter;
import net.chinhung.application.endpoint.order.CreateOrder;
import net.chinhung.application.order.Order;
import net.chinhung.application.component.order.OrderComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

public class CreateOrderServiceTest {

    @Mock
    private OrderComponent orderComponent;
    @Mock private OrderConverter orderConverter;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        final CreateOrderService instance = new CreateOrderService(orderComponent, orderConverter);

        final CreateOrder anyCommand = new CreateOrder();
        anyCommand.setOrderLines(new ArrayList<>());
        instance.execute(anyCommand);

        Mockito.verify(orderComponent).create(Mockito.any());
        Mockito.verify(orderConverter).toOrderDTO((Order) Mockito.any());
    }
}
