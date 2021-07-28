package net.chinhung.core.component.order;

import net.chinhung.core.order.CoreOrder;

import java.util.Optional;

public class FindByIdResult {

    private final Optional<CoreOrder> founded;

    public FindByIdResult(final Optional<CoreOrder> founded) {
        this.founded = founded;
    }

    public Optional<CoreOrder> getFounded() {
        return founded;
    }
}
