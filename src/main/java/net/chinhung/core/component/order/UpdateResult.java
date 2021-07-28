package net.chinhung.core.component.order;

import net.chinhung.core.order.CoreOrder;

public class UpdateResult {

    private final CoreOrder updated;

    public UpdateResult(final CoreOrder updated) {
        this.updated = updated;
    }

    public CoreOrder getUpdated() {
        return updated;
    }
}
