package net.chinhung.application.component.inventory;

import net.chinhung.application.inventory.InventoryRecord;

import java.util.List;
import java.util.stream.Stream;

public class GetAllResult {

    private final List<InventoryRecord> founded;

    public GetAllResult(final List<InventoryRecord> founded) {
        this.founded = founded;
    }

    public Stream<InventoryRecord> getFounded() {
        return founded.stream();
    }
}
