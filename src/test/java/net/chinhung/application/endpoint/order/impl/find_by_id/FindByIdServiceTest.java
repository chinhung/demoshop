package net.chinhung.application.endpoint.order.impl.find_by_id;

import net.chinhung.application.endpoint.order.impl.OrderConverter;
import net.chinhung.application.order.Order;
import net.chinhung.application.component.order.OrderComponent;
import net.chinhung.core.order.CoreOrder;
import net.chinhung.core.order.CoreStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static util.TestUtil.ANY_STRING;

public class FindByIdServiceTest {

    @Mock private OrderComponent orderComponent;
    @Mock private OrderConverter orderConverter;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        final Optional<Order> anyResult = Optional.of(new Order(new CoreOrder(ANY_STRING, new ArrayList<>(), CoreStatus.APPROVED)));
        Mockito.when(orderComponent.findById(Mockito.any())).thenReturn(anyResult);
        final FindByIdService findByIdService = new FindByIdService(orderComponent, orderConverter);

        final FindById anyQuery = new FindById(ANY_STRING);
        findByIdService.execute(anyQuery);

        Mockito.verify(orderComponent).findById(Mockito.any());
        Mockito.verify(orderConverter).toOrderDTO((Order) Mockito.any());
    }
}
