package net.chinhung.application.product.lazy_stock_quantity;

import net.chinhung.application.component.inventory.InventoryComponent;

import java.util.List;

public class StockQuantityStoreProvider {

    private final InventoryComponent inventoryComponent;

    public StockQuantityStoreProvider(InventoryComponent inventoryComponent) {
        this.inventoryComponent = inventoryComponent;
    }

    public StockQuantityStore provide(final List<String> productIdGroup) {
        return new StockQuantityStore(inventoryComponent, productIdGroup);
    }
}
