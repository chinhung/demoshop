package net.chinhung.application.inventory;

import net.chinhung.core.inventory.CoreInventoryRecord;

public class InventoryRecord {

    private final CoreInventoryRecord coreInventoryRecord;

    public InventoryRecord(final CoreInventoryRecord coreInventoryRecord) {
        this.coreInventoryRecord = coreInventoryRecord;
    }

    public String getId() {
        return coreInventoryRecord.getId();
    }

    public String getName() {
        return coreInventoryRecord.getName();
    }

    public String getProductId() {
        return coreInventoryRecord.getProductId();
    }

    public Integer getStockQuantity() {
        return coreInventoryRecord.getStockQuantity();
    }
}
