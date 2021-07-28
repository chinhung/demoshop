package net.chinhung.application.component.endpoint.impl.order_endpoint;

import net.chinhung.application.endpoint.order.OrderDTO;
import net.chinhung.application.endpoint.order.OrderLineDTO;
import net.chinhung.application.endpoint.order.impl.OrderConverter;
import net.chinhung.application.order.Item;
import net.chinhung.application.order.Order;
import net.chinhung.core.order.CoreItem;
import net.chinhung.core.order.CoreOrder;
import net.chinhung.core.order.CoreStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static util.TestUtil.ANY_INT;
import static util.TestUtil.ANY_STRING;

public class OrderConverterTest {

    @Test
    public void testConvert() {
        final OrderConverter converter = new OrderConverter();
        final Order order = new Order(new CoreOrder(ANY_STRING, Collections.singletonList(new CoreItem(ANY_STRING, ANY_STRING, ANY_INT)), CoreStatus.APPROVED));

        final OrderDTO dto = converter.toOrderDTO(order);

        Assertions.assertEquals(order.getId(), dto.getId());
        Assertions.assertEquals(order.getStatus().toString(), dto.getStatus());
        Assertions.assertEquals(1, order.getItems().size());
        Assertions.assertEquals(order.getItems().size(), dto.getOrderLines().size());
        final Item item = order.getItems().get(0);
        final OrderLineDTO orderLineDTO = dto.getOrderLines().get(0);
        Assertions.assertEquals(item.getName(), orderLineDTO.getName());
        Assertions.assertEquals(item.getProductId(), orderLineDTO.getProductId());
        Assertions.assertEquals(item.getQuantity(), orderLineDTO.getQuantity());
    }
}
