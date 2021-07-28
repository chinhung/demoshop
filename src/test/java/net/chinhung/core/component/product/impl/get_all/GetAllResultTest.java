package net.chinhung.core.component.product.impl.get_all;

import net.chinhung.core.component.product.GetAllResult;
import net.chinhung.core.product.CoreProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.Stream;

public class GetAllResultTest {

    @Test
    public void testGetFounded() {
        final GetAllResult result = new GetAllResult(new ArrayList<>());

        final Stream<CoreProduct> stream1 = result.getFounded();
        final Stream<CoreProduct> stream2 = result.getFounded();

        Assertions.assertNotSame(stream1, stream2);
    }
}
