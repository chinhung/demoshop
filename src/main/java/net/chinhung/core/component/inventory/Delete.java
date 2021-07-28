package net.chinhung.core.component.inventory;

import javax.validation.constraints.NotEmpty;

public class Delete {

    @NotEmpty
    private final String id;

    public Delete(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
