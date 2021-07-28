package net.chinhung.application.component.product.impl;

import io.github.chinhung.pointwave.PointWave;
import net.chinhung.application.component.product.Create;
import net.chinhung.application.component.product.CreateResult;
import net.chinhung.application.component.product.GetAll;
import net.chinhung.application.component.product.GetAllResult;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;
import net.chinhung.fundamental.aspect.listener.AfterDecorator;
import net.chinhung.fundamental.aspect.listener.AfterListener;

import java.util.function.Function;

class ProductServiceFactory {

    static QueryService<GetAll, GetAllResult> getAllService(
            final QueryService<GetAll, GetAllResult> implementer
    ) {
        return PointWave.decoratee(implementer)
                .decorated(q -> new AfterDecorator<>(q, new AfterListener<GetAll, GetAllResult>() {
                    @Override
                    public void onAfter(GetAll o, GetAllResult o2) {
                        System.out.println("-- after getAll --");
                        System.out.println("GetAll:" + o);
                        System.out.println("GetAllResult: " + o2);
                        System.out.println("-- after getAll --");
                    }
                }))
                .complete();
    }

    static CommandService<Create, CreateResult> createService(
            final CommandService<Create, CreateResult> implementer,
            final Function<CommandService<Create, CreateResult>, CommandService<Create, CreateResult>> decorateWithActionValidator
    ) {
        return PointWave.decoratee(implementer)
                .decorated(decorateWithActionValidator)
                .decorated(c -> new AfterDecorator<>(c, new AfterListener<Create, CreateResult>() {
                    @Override
                    public void onAfter(Create o, CreateResult o2) {
                        System.out.println("-- after create --");
                        System.out.println("Create:" + o);
                        System.out.println("CreateResult: " + o2);
                        System.out.println("-- after create --");
                    }
                }))
                .complete();
    }
}
