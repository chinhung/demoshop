package net.chinhung.core.component.inventory;

import net.chinhung.core.inventory.CoreInventoryRecord;

public class CreateResult {

    private final CoreInventoryRecord created;

    public CreateResult(final CoreInventoryRecord created) {
        this.created = created;
    }

    public CoreInventoryRecord getCreated() {
        return created;
    }
}
