package net.chinhung.application.component.order.impl.get_all;

import net.chinhung.application.component.order.GetAll;
import net.chinhung.application.component.order.GetAllResult;
import net.chinhung.application.order.Order;
import net.chinhung.core.component.order.CoreOrderComponent;
import net.chinhung.fundamental.aspect.QueryService;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllService implements QueryService<GetAll, GetAllResult> {

    private final CoreOrderComponent coreOrderComponent;

    GetAllService(final CoreOrderComponent coreOrderComponent) {
        this.coreOrderComponent = coreOrderComponent;
    }

    @Override
    public GetAllResult execute(final GetAll command) {
        final net.chinhung.core.component.order.GetAllResult result = coreOrderComponent.getAll(new net.chinhung.core.component.order.GetAll());
        final List<Order> founded = result.getFounded().map(Order::new).collect(Collectors.toList());
        return new GetAllResult(founded);
    }

    public static QueryService<GetAll, GetAllResult> getImplementer(final CoreOrderComponent coreOrderComponent) {
        return new GetAllService(coreOrderComponent);
    }
}
