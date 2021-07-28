package net.chinhung.core.component.order;

import javax.validation.constraints.NotEmpty;

public class FindById {

    @NotEmpty
    private final String id;

    public FindById(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
