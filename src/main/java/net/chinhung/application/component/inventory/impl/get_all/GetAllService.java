package net.chinhung.application.component.inventory.impl.get_all;

import net.chinhung.application.component.inventory.GetAll;
import net.chinhung.application.component.inventory.GetAllResult;
import net.chinhung.application.inventory.InventoryRecord;
import net.chinhung.core.component.inventory.CoreInventoryComponent;
import net.chinhung.fundamental.aspect.QueryService;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllService implements QueryService<GetAll, GetAllResult>  {

    private final CoreInventoryComponent coreInventoryComponent;

    public GetAllService(final CoreInventoryComponent coreInventoryComponent) {
        this.coreInventoryComponent = coreInventoryComponent;
    }

    @Override
    public GetAllResult execute(final GetAll getAll) {
        final net.chinhung.core.component.inventory.GetAllResult result = coreInventoryComponent.getAll(new net.chinhung.core.component.inventory.GetAll());
        final List<InventoryRecord> records = result.getFounded().map(InventoryRecord::new).collect(Collectors.toList());
        return new GetAllResult(records);
    }

    public static QueryService<GetAll, GetAllResult> getImplementer(final CoreInventoryComponent coreInventoryComponent) {
        return new GetAllService(coreInventoryComponent);
    }
}
