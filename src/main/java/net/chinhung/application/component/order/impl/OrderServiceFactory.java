package net.chinhung.application.component.order.impl;

import io.github.chinhung.pointwave.PointWave;
import net.chinhung.application.component.order.Create;
import net.chinhung.application.component.order.CreateResult;
import net.chinhung.application.component.order.FindById;
import net.chinhung.application.component.order.FindByIdResult;
import net.chinhung.application.component.order.GetAll;
import net.chinhung.application.component.order.GetAllResult;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;

import java.util.function.Function;

class OrderServiceFactory {

    static QueryService<GetAll, GetAllResult> getAllService(final QueryService<GetAll, GetAllResult> implementer) {
        return implementer;
    }

    static QueryService<FindById, FindByIdResult> findByIdService(
            final QueryService<FindById, FindByIdResult> implementer,
            final Function<QueryService<FindById, FindByIdResult>, QueryService<FindById, FindByIdResult>> decorateWithActionValidator
    ) {
        return PointWave.decoratee(implementer)
                .decorated(decorateWithActionValidator)
                .complete();
    }

    static CommandService<Create, CreateResult> createService(
            final CommandService<Create, CreateResult> implementer,
            final Function<CommandService<Create, CreateResult>, CommandService<Create, CreateResult>> decorateWithInventoryQuery,
            final Function<CommandService<Create, CreateResult>, CommandService<Create, CreateResult>> decorateWithInventoryUpdate,
            final Function<CommandService<Create, CreateResult>, CommandService<Create, CreateResult>> decorateWithActionValidator
    ) {
        return PointWave.decoratee(implementer)
                .decorated(decorateWithInventoryQuery)
                .decorated(decorateWithInventoryUpdate)
                .decorated(decorateWithActionValidator)
                .complete();
    }
}
