package net.chinhung.application.product.lazy_stock_quantity;

import net.chinhung.application.inventory.InventoryRecord;
import net.chinhung.application.component.inventory.InventoryComponent;
import net.chinhung.core.inventory.CoreInventoryRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.stream.Stream;

import static util.TestUtil.ANY_STRING;

public class StockQuantityStoreTest {

    @Mock private InventoryComponent inventoryComponent;
    private StockQuantityStore store;

    private final String someProductId = "someProductId";
    private final Integer stockQuantityOfSomeProductId = 1;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        Mockito.when(inventoryComponent.getInventoryRecords(Mockito.anyList()))
                .thenReturn(Stream.of(new InventoryRecord(new CoreInventoryRecord(ANY_STRING,  ANY_STRING, someProductId, stockQuantityOfSomeProductId))));
        store = new StockQuantityStore(inventoryComponent, Mockito.anyList());
    }

    @Test
    public void testFindByProductId() {
        final Integer founded = store.findByProductId(someProductId);

        Assertions.assertEquals(stockQuantityOfSomeProductId, founded);
    }

    @Test
    public void testFindByProductIdDefaultValue() {
        final Integer defaultStockQuantity = 0;
        final String anotherProductId = "anotherProductId";

        final Integer founded = store.findByProductId(anotherProductId);

        Assertions.assertEquals(defaultStockQuantity, founded);
    }

    @Test
    public void testQueryWillExecuteOnlyOnce() throws InterruptedException {
        store.findByProductId(someProductId);
        store.findByProductId(someProductId);

        Mockito.verify(inventoryComponent, Mockito.times(1)).getInventoryRecords(Mockito.anyList());
    }
}
