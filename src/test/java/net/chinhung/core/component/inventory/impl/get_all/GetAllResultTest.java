package net.chinhung.core.component.inventory.impl.get_all;

import net.chinhung.core.component.inventory.GetAllResult;
import net.chinhung.core.inventory.CoreInventoryRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.Stream;

public class GetAllResultTest {

    @Test
    public void testGetFounded() {
        final GetAllResult result = new GetAllResult(new ArrayList<>());

        final Stream<CoreInventoryRecord> stream1 = result.getFounded();
        final Stream<CoreInventoryRecord> stream2 = result.getFounded();

        Assertions.assertNotSame(stream1, stream2);
    }
}
