package net.chinhung.core.component.product.impl;

import io.github.chinhung.pointwave.PointWave;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;
import net.chinhung.core.component.product.impl.create.CreateService;
import net.chinhung.core.component.product.impl.delete.DeleteService;
import net.chinhung.core.component.product.impl.get_all.GetAllService;
import net.chinhung.core.component.product.impl.update.UpdateService;
import net.chinhung.core.component.product.*;

import java.util.function.Function;

class CoreProductServiceFactory {

    static QueryService<GetAll, GetAllResult> getAllService(final CoreProductDataSource dataSource) {
        return new GetAllService(dataSource);
    }

    static CommandService<Create, CreateResult> createService(
            final CoreProductDataSource dataSource,
            final Function<CommandService<Create, CreateResult>, CommandService<Create, CreateResult>> decorateWithActionValidator
    ) {
        return PointWave.decoratee((CommandService<Create, CreateResult>) new CreateService(dataSource))
                .decorated(decorateWithActionValidator)
                .complete();
    }

    static CommandService<Update, UpdateResult> updateService(
            final CoreProductDataSource dataSource,
            final Function<CommandService<Update, UpdateResult>, CommandService<Update, UpdateResult>> decorateWithActionValidator
    ) {
        return PointWave.decoratee((CommandService<Update, UpdateResult>) new UpdateService(dataSource))
                .decorated(decorateWithActionValidator)
                .complete();
    }

    static CommandService<Delete, DeleteResult> deleteService(
            final CoreProductDataSource dataSource,
            final Function<CommandService<Delete, DeleteResult>, CommandService<Delete, DeleteResult>> decorateWithActionValidator
    ) {
        return PointWave.decoratee((CommandService<Delete, DeleteResult>) new DeleteService(dataSource))
                .decorated(decorateWithActionValidator)
                .complete();
    }
}
