package net.chinhung.application.component.inventory.impl;

import net.chinhung.application.component.inventory.InventoryComponent;
import net.chinhung.application.component.inventory.impl.get_all.GetAllService;
import net.chinhung.application.component.inventory.impl.update.UpdateService;
import net.chinhung.application.inventory.InventoryRecord;
import net.chinhung.application.component.inventory.GetAll;
import net.chinhung.application.component.inventory.GetAllResult;
import net.chinhung.application.component.inventory.InventoryQuery;
import net.chinhung.application.component.inventory.InventoryQueryResult;
import net.chinhung.application.component.inventory.UpdateResult;
import net.chinhung.application.component.inventory.Update;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;
import net.chinhung.fundamental.aspect.validator.DecorateWithDefaultActionValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InventoryComponentImpl implements InventoryComponent {

    private final QueryService<GetAll, GetAllResult> getAllService;
    private final CommandService<Update, UpdateResult> updateService;

    InventoryComponentImpl(
            final QueryService<GetAll, GetAllResult> getAllService,
            final CommandService<Update, UpdateResult> updateService
    ) {
        this.getAllService = getAllService;
        this.updateService = updateService;
    }

    @Override
    public Stream<InventoryRecord> getInventoryRecords(final List<String> productIds) {
        final GetAllResult result = getAllService.execute(new GetAll());
        final List<InventoryRecord> filtered = result.getFounded().filter(record -> productIds.contains(record.getProductId())).collect(Collectors.toList());
        return filtered.stream();
    }

    @Override
    public InventoryQueryResult query(final List<InventoryQuery> queries) {
        final InventoryQueryResult result = new InventoryQueryResult();
        final GetAllResult getAllResult = getAllService.execute(new GetAll());
        queries.stream().forEach(query -> {
            Optional<InventoryRecord> filtered = getAllResult.getFounded().filter(record -> record.getProductId().equals(query.getProductId())).findFirst();
            if (filtered.isPresent() && (filtered.get().getStockQuantity() >= query.getQuantity())) {
                result.inStock(query);
            } else {
                result.outOfStock(query);
            }
        });
        return result;
    }

    @Override
    public InventoryRecord update(final String productId, final Integer quantity) {
        return updateService.execute(new Update(productId, quantity)).getUpdated();
    }

    public static InventoryComponent factory(final InventoryResource resource) {
        final QueryService<GetAll, GetAllResult> getAllServiceImpl = GetAllService.getImplementer(resource.coreInventoryComponent());
        final CommandService<Update, UpdateResult> updateServiceImpl = UpdateService.getImplementer(resource.coreInventoryComponent());

        final QueryService<GetAll, GetAllResult> getAllService = InventoryServiceFactory.getAllService(getAllServiceImpl);
        final CommandService<Update, UpdateResult> updateService = InventoryServiceFactory.updateService(updateServiceImpl, new DecorateWithDefaultActionValidator<>());
        return new InventoryComponentImpl(getAllService, updateService);
    }
}
