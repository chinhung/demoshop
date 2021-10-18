package net.chinhung.application.product.lazy_stock_quantity;

import net.chinhung.application.component.inventory.InventoryComponent;
import net.chinhung.application.component.inventory.InventoryQuery;
import net.chinhung.application.component.inventory.InventoryQueryResult;
import net.chinhung.application.inventory.InventoryRecord;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

public class WaitingInventoryCompoonent implements InventoryComponent {

    private final Stream<InventoryRecord> inventoryRecords;

    private ReentrantLock lock = new ReentrantLock();

    public WaitingInventoryCompoonent(final Stream<InventoryRecord> inventoryRecords) {
        this.inventoryRecords = inventoryRecords;
    }

    @Override
    public Stream<InventoryRecord> getInventoryRecords(final List<String> productIds) {
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
        }
        return inventoryRecords;
    }

    @Override
    public InventoryQueryResult query(final List<InventoryQuery> queries) {
        throw new RuntimeException();
    }

    @Override
    public InventoryRecord update(final String productId, final Integer quantity) {
        throw new RuntimeException();
    }
}
