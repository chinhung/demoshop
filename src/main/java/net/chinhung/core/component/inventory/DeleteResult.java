package net.chinhung.core.component.inventory;

import net.chinhung.core.inventory.CoreInventoryRecord;

public class DeleteResult {

    private final CoreInventoryRecord deleted;

    public DeleteResult(final CoreInventoryRecord deleted) {
        this.deleted = deleted;
    }

    public CoreInventoryRecord getDeleted() {
        return deleted;
    }
}
