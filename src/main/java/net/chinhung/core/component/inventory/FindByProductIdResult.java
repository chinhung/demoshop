package net.chinhung.core.component.inventory;

import net.chinhung.core.inventory.CoreInventoryRecord;

import java.util.Optional;

public class FindByProductIdResult {

    private final Optional<CoreInventoryRecord> inventoryRecord;

    public FindByProductIdResult(final Optional<CoreInventoryRecord> inventoryRecord) {
        this.inventoryRecord = inventoryRecord;
    }

    public Optional<CoreInventoryRecord> getFounded() {
        return inventoryRecord;
    }
}
