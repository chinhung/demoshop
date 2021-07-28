package net.chinhung.core.component.inventory;

import net.chinhung.core.inventory.CoreInventoryRecord;

import java.util.List;
import java.util.stream.Stream;

public class GetAllResult {

    private final List<CoreInventoryRecord> founded;

    public GetAllResult(final List<CoreInventoryRecord> founded) {
        this.founded = founded;
    }

    public Stream<CoreInventoryRecord> getFounded() {
        return founded.stream();
    }
}
