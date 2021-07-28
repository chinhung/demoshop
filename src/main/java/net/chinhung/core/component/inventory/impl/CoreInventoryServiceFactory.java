package net.chinhung.core.component.inventory.impl;

import io.github.chinhung.pointwave.PointWave;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;
import net.chinhung.core.component.inventory.Create;
import net.chinhung.core.component.inventory.CreateResult;
import net.chinhung.core.component.inventory.impl.create.CreateService;
import net.chinhung.core.component.inventory.Delete;
import net.chinhung.core.component.inventory.DeleteResult;
import net.chinhung.core.component.inventory.impl.delete.DeleteService;
import net.chinhung.core.component.inventory.FindByProductId;
import net.chinhung.core.component.inventory.FindByProductIdResult;
import net.chinhung.core.component.inventory.impl.find_by_product_id.FindByProductIdService;
import net.chinhung.core.component.inventory.GetAll;
import net.chinhung.core.component.inventory.GetAllResult;
import net.chinhung.core.component.inventory.impl.get_all.GetAllService;
import net.chinhung.core.component.inventory.Update;
import net.chinhung.core.component.inventory.UpdateResult;
import net.chinhung.core.component.inventory.impl.update.UpdateService;

import java.util.function.Function;

class CoreInventoryServiceFactory {

    static QueryService<GetAll, GetAllResult> getAllService(final CoreInventoryDataSource dataSource) {
        return new GetAllService(dataSource);
    }

    static QueryService<FindByProductId, FindByProductIdResult> findByProductIdService(
            final CoreInventoryDataSource dataSource,
            final Function<QueryService<FindByProductId, FindByProductIdResult>, QueryService<FindByProductId, FindByProductIdResult>> decorateWithActionValidator
    ) {
        return PointWave.decoratee((QueryService<FindByProductId, FindByProductIdResult>) new FindByProductIdService(dataSource))
                .decorated(decorateWithActionValidator)
                .complete();
    }

    static CommandService<Create, CreateResult> createService(
            final CoreInventoryDataSource dataSource,
            final Function<CommandService<Create, CreateResult>, CommandService<Create, CreateResult>> decorateWithActionValidator
    ) {
        return PointWave.decoratee((CommandService<Create, CreateResult>) new CreateService(dataSource))
                .decorated(decorateWithActionValidator)
                .complete();
    }

    static CommandService<Update, UpdateResult> updateService(
            final CoreInventoryDataSource dataSource,
            final Function<CommandService<Update, UpdateResult>, CommandService<Update, UpdateResult>> decorateWithActionValidator
    ) {
        return PointWave.decoratee((CommandService<Update, UpdateResult>) new UpdateService(dataSource))
                .decorated(decorateWithActionValidator)
                .complete();
    }

    static CommandService<Delete, DeleteResult> deleteService(
            final CoreInventoryDataSource dataSource,
            final Function<CommandService<Delete, DeleteResult>, CommandService<Delete, DeleteResult>> decorateWithActionValidator
    ) {
        return PointWave.decoratee((CommandService<Delete, DeleteResult>) new DeleteService(dataSource))
                .decorated(decorateWithActionValidator)
                .complete();
    }
}
