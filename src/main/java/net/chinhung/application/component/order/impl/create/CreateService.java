package net.chinhung.application.component.order.impl.create;

import net.chinhung.application.component.order.Create;
import net.chinhung.application.component.order.CreateResult;
import net.chinhung.application.order.Order;
import net.chinhung.core.order.CoreItem;
import net.chinhung.core.component.order.CoreOrderComponent;
import net.chinhung.fundamental.aspect.CommandService;

import java.util.List;
import java.util.stream.Collectors;

public class CreateService implements CommandService<Create, CreateResult> {

    private final CoreOrderComponent coreOrderComponent;

    CreateService(final CoreOrderComponent coreOrderComponent) {
        this.coreOrderComponent = coreOrderComponent;
    }

    @Override
    public CreateResult execute(Create command) {
        final List<CoreItem> coreItems = command.getItems().stream().map(item -> {
            return new CoreItem(item.getName(), item.getProductId(), item.getQuantity());
        }).collect(Collectors.toList());
        final net.chinhung.core.component.order.CreateResult result = coreOrderComponent.create(new net.chinhung.core.component.order.Create(coreItems));
        return new CreateResult(new Order(result.getCreated()));
    }

    public static CommandService<Create, CreateResult> getImplementer(final CoreOrderComponent coreOrderComponent) {
        return new CreateService(coreOrderComponent);
    }
}
