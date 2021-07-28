package net.chinhung.application.component.order.impl.get_all;

import net.chinhung.application.component.order.GetAllResult;
import net.chinhung.application.order.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.Stream;

public class GetAllResultTest {

    @Test
    public void testGetFounded() {
        final GetAllResult result = new GetAllResult(new ArrayList<>());

        final Stream<Order> stream1 = result.getFounded();
        final Stream<Order> stream2 = result.getFounded();

        Assertions.assertNotSame(stream1, stream2);
    }
}
