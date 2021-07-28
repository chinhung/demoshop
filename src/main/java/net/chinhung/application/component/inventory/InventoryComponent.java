package net.chinhung.application.component.inventory;

import net.chinhung.application.inventory.InventoryRecord;

import java.util.List;
import java.util.stream.Stream;

public interface InventoryComponent {

    Stream<InventoryRecord> getInventoryRecords(List<String> productIds);

    InventoryQueryResult query(List<InventoryQuery> queries);

    InventoryRecord update(String productId, Integer quantity);
}
