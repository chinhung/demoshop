package net.chinhung.core.component.order;

import net.chinhung.core.order.CoreOrder;

public class DeleteResult {

    private final CoreOrder deleted;

    public DeleteResult(final CoreOrder deleted) {
        this.deleted = deleted;
    }

    public CoreOrder getDeleted() {
        return deleted;
    }
}
