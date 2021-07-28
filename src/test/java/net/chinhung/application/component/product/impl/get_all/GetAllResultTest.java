package net.chinhung.application.component.product.impl.get_all;

import net.chinhung.application.component.product.GetAllResult;
import net.chinhung.application.product.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.Stream;

public class GetAllResultTest {

    @Test
    public void testGetFounded() {
        final GetAllResult result = new GetAllResult(new ArrayList<>());

        final Stream<Product> stream1 = result.getFounded();
        final Stream<Product> stream2 = result.getFounded();

        Assertions.assertNotSame(stream1, stream2);
    }
}
