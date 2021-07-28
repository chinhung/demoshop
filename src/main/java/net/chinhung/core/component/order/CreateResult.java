package net.chinhung.core.component.order;

import net.chinhung.core.order.CoreOrder;

public class CreateResult {

    private final CoreOrder created;

    public CreateResult(final CoreOrder created) {
        this.created = created;
    }

    public CoreOrder getCreated() {
        return created;
    }
}
