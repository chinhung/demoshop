package net.chinhung.application.component.order.impl.find_by_id;

import net.chinhung.application.component.order.FindById;
import net.chinhung.application.component.order.FindByIdResult;
import net.chinhung.application.order.Order;
import net.chinhung.core.order.CoreOrder;
import net.chinhung.core.component.order.CoreOrderComponent;
import net.chinhung.fundamental.aspect.QueryService;

import java.util.Optional;

public class FindByIdService implements QueryService<FindById, FindByIdResult> {

    private final CoreOrderComponent coreOrderComponent;

    FindByIdService(final CoreOrderComponent coreOrderComponent) {
        this.coreOrderComponent = coreOrderComponent;
    }

    @Override
    public FindByIdResult execute(final FindById command) {
        final net.chinhung.core.component.order.FindByIdResult result = coreOrderComponent.findById(new net.chinhung.core.component.order.FindById(command.getId()));
        final Optional<CoreOrder> founded = result.getFounded();
        if (founded.isPresent()) {
            return new FindByIdResult(Optional.of(new Order(founded.get())));
        }
        return new FindByIdResult(Optional.empty());
    }

    public static QueryService<FindById, FindByIdResult> getImplementer(final CoreOrderComponent coreOrderComponent) {
        return new FindByIdService(coreOrderComponent);
    }
}
