package net.chinhung.core.component.order;

import net.chinhung.core.order.CoreStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Update {

    @NotEmpty
    private final String id;

    @NotNull
    private final CoreStatus coreStatus;

    public Update(final String id, final CoreStatus coreStatus) {
        this.id = id;
        this.coreStatus = coreStatus;
    }

    public String getId() {
        return id;
    }

    public CoreStatus getStatus() {
        return coreStatus;
    }
}
