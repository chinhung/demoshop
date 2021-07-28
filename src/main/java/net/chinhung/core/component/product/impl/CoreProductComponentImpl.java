package net.chinhung.core.component.product.impl;

import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;
import net.chinhung.fundamental.aspect.validator.DecorateWithDefaultActionValidator;
import net.chinhung.core.component.product.*;

public class CoreProductComponentImpl implements CoreProductComponent {

    private final QueryService<GetAll, GetAllResult> getAllService;
    private final CommandService<Create, CreateResult> createService;
    private final CommandService<Delete, DeleteResult> deleteService;
    private final CommandService<Update, UpdateResult> updateService;

    CoreProductComponentImpl(
            final QueryService<GetAll, GetAllResult> getAllService,
            final CommandService<Create, CreateResult> createService,
            final CommandService<Delete, DeleteResult> deleteService,
            final CommandService<Update, UpdateResult> updateService
    ) {
        this.getAllService = getAllService;
        this.createService = createService;
        this.deleteService = deleteService;
        this.updateService = updateService;
    }

    @Override
    public GetAllResult getAll(final GetAll query) {
        return getAllService.execute(query);
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

    public static CoreProductComponent factory(final CoreProductResource coreProductResource) {
        final CoreProductDataSource dataSource = coreProductResource.coreProductDataSource();

        final QueryService<GetAll, GetAllResult> getAllService = CoreProductServiceFactory.getAllService(dataSource);
        final CommandService<Create, CreateResult> createService = CoreProductServiceFactory.createService(dataSource, new DecorateWithDefaultActionValidator<>());
        final CommandService<Delete, DeleteResult> deleteService = CoreProductServiceFactory.deleteService(dataSource, new DecorateWithDefaultActionValidator<>());
        final CommandService<Update, UpdateResult> updateService = CoreProductServiceFactory.updateService(dataSource, new DecorateWithDefaultActionValidator<>());

        return new CoreProductComponentImpl(getAllService, createService, deleteService, updateService);
    }
}
