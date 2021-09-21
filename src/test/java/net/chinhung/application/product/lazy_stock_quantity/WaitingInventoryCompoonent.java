package net.chinhung.application.product.lazy_stock_quantity;

import net.chinhung.application.component.inventory.InventoryComponent;
import net.chinhung.application.component.inventory.InventoryQuery;
import net.chinhung.application.component.inventory.InventoryQueryResult;
import net.chinhung.application.inventory.InventoryRecord;

import java.util.List;
import java.util.stream.Stream;

public class WaitingInventoryCompoonent implements InventoryComponent {

    private boolean keepWaiting = true;
    private final Stream<InventoryRecord> inventoryRecords;

    public final long duration = 500;

    public WaitingInventoryCompoonent(final Stream<InventoryRecord> inventoryRecords) {
        this.inventoryRecords = inventoryRecords;
    }

    @Override
    public Stream<InventoryRecord> getInventoryRecords(final List<String> productIds) {
        while (keepWaiting) {
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return inventoryRecords;
    }

    public void waitingEnd() {
        keepWaiting = false;
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
