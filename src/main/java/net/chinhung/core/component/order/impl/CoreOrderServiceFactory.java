package net.chinhung.core.component.order.impl;

import io.github.chinhung.pointwave.PointWave;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;
import net.chinhung.core.component.order.Create;
import net.chinhung.core.component.order.CreateResult;
import net.chinhung.core.component.order.impl.create.CreateService;
import net.chinhung.core.component.order.Delete;
import net.chinhung.core.component.order.DeleteResult;
import net.chinhung.core.component.order.impl.delete.DeleteService;
import net.chinhung.core.component.order.FindById;
import net.chinhung.core.component.order.FindByIdResult;
import net.chinhung.core.component.order.impl.find_by_id.FindByIdService;
import net.chinhung.core.component.order.GetAll;
import net.chinhung.core.component.order.GetAllResult;
import net.chinhung.core.component.order.impl.get_all.GetAllService;
import net.chinhung.core.component.order.Update;
import net.chinhung.core.component.order.UpdateResult;
import net.chinhung.core.component.order.impl.update.UpdateService;

import java.util.function.Function;

class CoreOrderServiceFactory {

    static QueryService<GetAll, GetAllResult> getAllService(final CoreOrderDataSource dataSource) {
        return new GetAllService(dataSource);
    }

    static CommandService<Create, CreateResult> createService(
            final CoreOrderDataSource dataSource,
            final Function<CommandService<Create, CreateResult>, CommandService<Create, CreateResult>> decorateWithActionValidator
    ) {
        return PointWave.decoratee((CommandService<Create, CreateResult>) new CreateService(dataSource))
                .decorated(decorateWithActionValidator)
                .complete();
    }

    static CommandService<Update, UpdateResult> updateService(
            final CoreOrderDataSource dataSource,
            final Function<CommandService<Update, UpdateResult>, CommandService<Update, UpdateResult>> decorateWithActionValidator
    ) {
        return PointWave.decoratee((CommandService<Update, UpdateResult>) new UpdateService(dataSource))
                .decorated(decorateWithActionValidator)
                .complete();
    }

    static CommandService<Delete, DeleteResult> deleteService(
            final CoreOrderDataSource dataSource,
            final Function<CommandService<Delete, DeleteResult>, CommandService<Delete, DeleteResult>> decorateWithActionValidator
    ) {
        return PointWave.decoratee((CommandService<Delete, DeleteResult>) new DeleteService(dataSource))
                .decorated(decorateWithActionValidator)
                .complete();
    }

    static QueryService<FindById, FindByIdResult> findByIdService(
            final CoreOrderDataSource dataSource,
            final Function<QueryService<FindById, FindByIdResult>, QueryService<FindById, FindByIdResult>> decorateWithActionValidator
    ) {
        return PointWave.decoratee((QueryService<FindById, FindByIdResult>) new FindByIdService(dataSource))
                .decorated(decorateWithActionValidator)
                .complete();
    }
}
