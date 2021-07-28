package net.chinhung.core.component.inventory;

import net.chinhung.core.inventory.CoreInventoryRecord;

public class UpdateResult {

    private final CoreInventoryRecord updated;

    public UpdateResult(final CoreInventoryRecord updated) {
        this.updated = updated;
    }

    public CoreInventoryRecord getUpdated() {
        return updated;
    }
}
