package net.chinhung.application.endpoint.order.impl.find_orders;

import net.chinhung.application.endpoint.order.impl.OrderConverter;
import net.chinhung.application.order.Order;
import net.chinhung.application.component.order.OrderComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.stream.Stream;

public class FindOrdersServiceTest {

    @Mock private OrderComponent orderComponent;
    @Mock private OrderConverter orderConverter;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        final FindOrdersService findOrdersService = new FindOrdersService(orderComponent, orderConverter);

        findOrdersService.execute(Mockito.any());

        Mockito.verify(orderComponent).findOrders(Mockito.any());
        Mockito.verify(orderConverter).toOrderDTO((Stream<Order>) Mockito.any());
    }
}
