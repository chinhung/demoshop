package net.chinhung.core.component.order.impl.get_all;

import net.chinhung.core.component.order.GetAllResult;
import net.chinhung.core.order.CoreOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.Stream;

public class GetAllResultTest {

    @Test
    public void testGetFounded() {
        final GetAllResult result = new GetAllResult(new ArrayList<>());

        final Stream<CoreOrder> stream1 = result.getFounded();
        final Stream<CoreOrder> stream2 = result.getFounded();

        Assertions.assertNotSame(stream1, stream2);
    }
}
