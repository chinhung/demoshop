package net.chinhung.core.component.inventory.impl;

import net.chinhung.core.component.inventory.*;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;
import net.chinhung.fundamental.aspect.validator.DecorateWithDefaultActionValidator;

public class CoreInventoryComponentImpl implements CoreInventoryComponent {

    private final QueryService<GetAll, GetAllResult> getAllService;
    private final QueryService<FindByProductId, FindByProductIdResult> findByProductIdService;
    private final CommandService<Create, CreateResult> createService;
    private final CommandService<Delete, DeleteResult> deleteService;
    private final CommandService<Update, UpdateResult> updateService;

    public CoreInventoryComponentImpl(
            final QueryService<GetAll, GetAllResult> getAllService,
            final QueryService<FindByProductId, FindByProductIdResult> findByProductIdService,
            final CommandService<Create, CreateResult> createService,
            final CommandService<Delete, DeleteResult> deleteService,
            final CommandService<Update, UpdateResult> updateService
    ) {
        this.getAllService = getAllService;
        this.findByProductIdService = findByProductIdService;
        this.createService = createService;
        this.deleteService = deleteService;
        this.updateService = updateService;
    }

    @Override
    public GetAllResult getAll(final GetAll query) {
        return getAllService.execute(query);
    }

    @Override
    public FindByProductIdResult findByProductId(final FindByProductId query) {
        return findByProductIdService.execute(query);
    }

    @Override
    public CreateResult create(final Create command) {
        return createService.execute(command);
    }

    @Override
    public DeleteResult delete(final Delete command) {
        return deleteService.execute(command);
    }

    @Override
    public UpdateResult update(final Update command) {
        return updateService.execute(command);
    }

    public static CoreInventoryComponent factory(final CoreInventoryResource resource) {
        final CoreInventoryDataSource dataSource = resource.coreInventoryDataSource();

        final QueryService<GetAll, GetAllResult> getAll = CoreInventoryServiceFactory.getAllService(dataSource);
        final QueryService<FindByProductId, FindByProductIdResult> findByProductId = CoreInventoryServiceFactory.findByProductIdService(dataSource, new DecorateWithDefaultActionValidator<>());
        final CommandService<Create, CreateResult> create = CoreInventoryServiceFactory.createService(dataSource, new DecorateWithDefaultActionValidator<>());
        final CommandService<Delete, DeleteResult> delete = CoreInventoryServiceFactory.deleteService(dataSource, new DecorateWithDefaultActionValidator<>());
        final CommandService<Update, UpdateResult> update = CoreInventoryServiceFactory.updateService(dataSource, new DecorateWithDefaultActionValidator<>());

        return new CoreInventoryComponentImpl(getAll, findByProductId, create, delete, update);
    }
}
