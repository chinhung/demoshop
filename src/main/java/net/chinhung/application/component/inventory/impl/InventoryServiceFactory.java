package net.chinhung.application.component.inventory.impl;

import io.github.chinhung.pointwave.PointWave;
import net.chinhung.application.component.inventory.GetAll;
import net.chinhung.application.component.inventory.GetAllResult;
import net.chinhung.application.component.inventory.UpdateResult;
import net.chinhung.application.component.inventory.Update;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;

import java.util.function.Function;

class InventoryServiceFactory {

    static QueryService<GetAll, GetAllResult> getAllService(
            final QueryService<GetAll, GetAllResult> implementer
    ) {
        return implementer;
    }

    static CommandService<Update, UpdateResult> updateService(
            final CommandService<Update, UpdateResult> implementer,
            final Function<CommandService<Update, UpdateResult>, CommandService<Update, UpdateResult>> decoratorWithActionValidator
            ) {
        return PointWave.decoratee(implementer)
                .decorated(decoratorWithActionValidator)
                .complete() ;
    }
}
