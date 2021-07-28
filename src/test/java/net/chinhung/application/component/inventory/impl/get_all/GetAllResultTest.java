package net.chinhung.application.component.inventory.impl.get_all;

import net.chinhung.application.component.inventory.GetAllResult;
import net.chinhung.application.inventory.InventoryRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.stream.Stream;

public class GetAllResultTest {

    @Test
    public void testGetFounded() {
        final GetAllResult result = new GetAllResult(new ArrayList<>());

        final Stream<InventoryRecord> stream1 = result.getFounded();
        final Stream<InventoryRecord> stream2 = result.getFounded();

        Assertions.assertNotSame(stream1, stream2);
    }
}
