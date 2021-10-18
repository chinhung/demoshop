package net.chinhung.application.product.lazy_stock_quantity;

import net.chinhung.application.inventory.InventoryRecord;
import net.chinhung.core.inventory.CoreInventoryRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.TestUtil.ANY_STRING;

public class StockQuantityStoreThreadTest {

    private WaitingInventoryCompoonent inventoryComponent;
    private StockQuantityStore store;

    private final String someProductId = "someProductId";
    private final Integer stockQuantityOfSomeProductId = 1;

    @BeforeEach
    public void init() {
        inventoryComponent = new WaitingInventoryCompoonent(Stream.of(new InventoryRecord(new CoreInventoryRecord(ANY_STRING, ANY_STRING, someProductId, stockQuantityOfSomeProductId))));
        store = new StockQuantityStore(inventoryComponent, Mockito.anyList());
    }

    @Test
    public void testTwoThread() throws InterruptedException {
        List<Integer> queryResult = new ArrayList<>();
        Thread thread1 = new Thread(() -> {
            queryResult.add(store.findByProductId(someProductId));
        });
        Thread thread2 = new Thread(() -> {
            queryResult.add(store.findByProductId(someProductId));
        });

        thread1.start();
        Thread.sleep(300);
        assertEquals(Thread.State.TIMED_WAITING, thread1.getState());

        thread2.start();
        Thread.sleep(300);
        assertEquals(Thread.State.BLOCKED, thread2.getState());

        thread1.interrupt();
        thread1.join();
        thread2.join();
        assertEquals(2, queryResult.size());
        assertEquals(queryResult.get(0), queryResult.get(1));
    }
}
