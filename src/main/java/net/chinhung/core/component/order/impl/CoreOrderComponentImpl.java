package net.chinhung.core.component.order.impl;

import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;
import net.chinhung.fundamental.aspect.validator.DecorateWithDefaultActionValidator;
import net.chinhung.core.component.order.*;

public class CoreOrderComponentImpl implements CoreOrderComponent {

    private final QueryService<GetAll, GetAllResult> getAllService;
    private final QueryService<FindById, FindByIdResult> findByIdService;
    private final CommandService<Create, CreateResult> createService;
    private final CommandService<Delete, DeleteResult> deleteService;
    private final CommandService<Update, UpdateResult> updateService;

    CoreOrderComponentImpl(
            final QueryService<GetAll, GetAllResult> getAllService,
            final QueryService<FindById, FindByIdResult> findByIdService,
            final CommandService<Create, CreateResult> createService,
            final CommandService<Delete, DeleteResult> deleteService,
            final CommandService<Update, UpdateResult> updateService
    ) {
        this.getAllService = getAllService;
        this.findByIdService = findByIdService;
        this.createService = createService;
        this.deleteService = deleteService;
        this.updateService = updateService;
    }

    @Override
    public GetAllResult getAll(final GetAll query) {
        return getAllService.execute(query);
    }

    @Override
    public FindByIdResult findById(final FindById query) {
        return findByIdService.execute(query);
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

    public static CoreOrderComponent factory(final CoreOrderResource resource) {
        final CoreOrderDataSource dataSource = resource.coreOrderDataSource();

        final QueryService<GetAll, GetAllResult> getAllService = CoreOrderServiceFactory.getAllService(dataSource);
        final QueryService<FindById, FindByIdResult> findByIdService = CoreOrderServiceFactory.findByIdService(dataSource, new DecorateWithDefaultActionValidator<>());
        final CommandService<Create, CreateResult> createService = CoreOrderServiceFactory.createService(dataSource, new DecorateWithDefaultActionValidator<>());
        final CommandService<Delete, DeleteResult> deleteService = CoreOrderServiceFactory.deleteService(dataSource, new DecorateWithDefaultActionValidator<>());
        final CommandService<Update, UpdateResult> updateService = CoreOrderServiceFactory.updateService(dataSource, new DecorateWithDefaultActionValidator<>());

        return new CoreOrderComponentImpl(getAllService, findByIdService, createService, deleteService, updateService);
    }
}
