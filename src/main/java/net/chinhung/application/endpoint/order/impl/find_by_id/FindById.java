package net.chinhung.application.endpoint.order.impl.find_by_id;

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
