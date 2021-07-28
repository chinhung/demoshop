package net.chinhung.application.product.lazy_stock_quantity;

import net.chinhung.application.component.inventory.InventoryComponent;
import net.chinhung.application.inventory.InventoryRecord;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class StockQuantityStore {

    private final InventoryComponent inventoryComponent;
    private final List<String> productIds;

    private volatile Map<String, Integer> stockQuantities;

    public StockQuantityStore(InventoryComponent inventoryComponent, List<String> productIds) {
        this.inventoryComponent = inventoryComponent;
        this.productIds = productIds;
    }

    public StockQuantityStore(InventoryComponent inventoryComponent, String productId) {
        this.inventoryComponent = inventoryComponent;
        this.productIds = Collections.singletonList(productId);
    }

    public Integer findByProductId(String productId) {
        if (stockQuantities == null) {
            synchronized(this) {
                if (stockQuantities == null) {
                    stockQuantities = queryStockQuantities();
                }
            }
        }
        return stockQuantities.getOrDefault(productId, 0);
    }

    private Map<String, Integer> queryStockQuantities() {
        Stream<InventoryRecord> inventoryRecords = inventoryComponent.getInventoryRecords(productIds);
        return inventoryRecords.collect(Collectors.toMap(InventoryRecord::getProductId, InventoryRecord::getStockQuantity));
    }
}
