package net.chinhung.application.product.lazy_stock_quantity;

import net.chinhung.application.inventory.InventoryRecord;
import net.chinhung.core.inventory.CoreInventoryRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static util.TestUtil.ANY_STRING;

public class StockQuantityStoreThreadTest {

    private WaitingInventoryCompoonent inventoryComponent;
    private StockQuantityStore store;

    private final String someProductId = "someProductId";
    private final Integer stockQuantityOfSomeProductId = 1;

    final List<Integer> stockQuantityContainer = new ArrayList<>();
    final Runnable runnable1 = new Runnable() {

        @Override
        public void run() {
            stockQuantityContainer.add(store.findByProductId(someProductId));
        }
    };
    final Runnable runnable2 = new Runnable() {

        @Override
        public void run() {
            stockQuantityContainer.add(store.findByProductId(someProductId));
        }
    };

    @BeforeEach
    public void init() {
        inventoryComponent = new WaitingInventoryCompoonent(Stream.of(new InventoryRecord(new CoreInventoryRecord(ANY_STRING, ANY_STRING, someProductId, stockQuantityOfSomeProductId))));
        store = new StockQuantityStore(inventoryComponent, Mockito.anyList());
    }

    @Test
    public void testTwoThread() throws InterruptedException {
        Thread thread1 = new Thread(runnable1);
        thread1.start();
        Thread thread2 = new Thread(runnable2);
        thread2.start();

        Assertions.assertEquals(Thread.State.TIMED_WAITING, thread1.getState());
        Assertions.assertEquals(Thread.State.BLOCKED, thread2.getState());

        inventoryComponent.waitingEnd();
        Thread.sleep(inventoryComponent.duration * 2);

        Assertions.assertEquals(2, stockQuantityContainer.size());
        Assertions.assertEquals(stockQuantityContainer.get(0), stockQuantityContainer.get(1));
    }
}
