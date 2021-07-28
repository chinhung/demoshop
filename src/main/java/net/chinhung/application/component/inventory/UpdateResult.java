package net.chinhung.application.component.inventory;

import net.chinhung.application.inventory.InventoryRecord;

public class UpdateResult {

    private final InventoryRecord updated;

    public UpdateResult(final InventoryRecord updated) {
        this.updated = updated;
    }

    public InventoryRecord getUpdated() {
        return updated;
    }
}
