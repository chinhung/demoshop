package net.chinhung.platform.resource;

import net.chinhung.core.inventory.CoreInventoryRecord;
import net.chinhung.core.component.inventory.impl.CoreInventoryDataSource;
import net.chinhung.core.component.inventory.Create;
import net.chinhung.core.component.inventory.CreateResult;
import net.chinhung.core.component.inventory.Delete;
import net.chinhung.core.component.inventory.DeleteResult;
import net.chinhung.core.component.inventory.FindByProductId;
import net.chinhung.core.component.inventory.FindByProductIdResult;
import net.chinhung.core.component.inventory.GetAll;
import net.chinhung.core.component.inventory.GetAllResult;
import net.chinhung.core.component.inventory.Update;
import net.chinhung.core.component.inventory.UpdateResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryInventoryDataSource implements CoreInventoryDataSource {

    private final List<CoreInventoryRecord> coreInventoryRecords = new ArrayList<>();

    public InMemoryInventoryDataSource() {
        CoreInventoryRecord iPhone = new CoreInventoryRecord("1", "iPhone", "1", 1);
        CoreInventoryRecord macbook = new CoreInventoryRecord("2", "macbook","2", 2);
        CoreInventoryRecord iMac = new CoreInventoryRecord("3", "iMac", "3", 0);
        coreInventoryRecords.add(iPhone);
        coreInventoryRecords.add(macbook);
        coreInventoryRecords.add(iMac);
    }

    @Override
    public CreateResult create(Create command) {
        throw new RuntimeException("create inventory record not support");
    }

    @Override
    public DeleteResult delete(Delete command) {
        throw new RuntimeException("delete inventory record not support");
    }

    @Override
    public FindByProductIdResult findByProductId(FindByProductId command) {
        throw new RuntimeException("find inventory record by productId not support");
    }

    @Override
    public GetAllResult getAll(GetAll command) {
        return new GetAllResult(new ArrayList<>(coreInventoryRecords));
    }

    @Override
    public UpdateResult update(Update command) {
        Optional<CoreInventoryRecord> founded = coreInventoryRecords.stream().filter(record -> record.getProductId().equals(command.getProductId())).findFirst();
        if (!founded.isPresent()) {
            throw new RuntimeException();
        }
        coreInventoryRecords.remove(founded.get());
        CoreInventoryRecord updated = new CoreInventoryRecord(founded.get().getId(), founded.get().getName(), founded.get().getProductId(), command.getQuantity());
        coreInventoryRecords.add(updated);
        return new UpdateResult(updated);
    }
}
