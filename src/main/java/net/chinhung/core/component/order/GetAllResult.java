package net.chinhung.core.component.order;

import net.chinhung.core.order.CoreOrder;

import java.util.List;
import java.util.stream.Stream;

public class GetAllResult {

    private final List<CoreOrder> founded;

    public GetAllResult(final List<CoreOrder> founded) {
        this.founded = founded;
    }

    public Stream<CoreOrder> getFounded() {
        return founded.stream();
    }
}
