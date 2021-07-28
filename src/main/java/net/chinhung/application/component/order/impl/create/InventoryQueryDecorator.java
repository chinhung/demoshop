package net.chinhung.application.component.order.impl.create;

import net.chinhung.application.component.order.Create;
import net.chinhung.application.component.order.CreateResult;
import net.chinhung.application.component.inventory.InventoryComponent;
import net.chinhung.application.component.inventory.InventoryQuery;
import net.chinhung.application.component.inventory.InventoryQueryResult;
import net.chinhung.fundamental.aspect.CommandService;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryQueryDecorator implements CommandService<Create, CreateResult> {

    private final CommandService<Create, CreateResult> decoratee;
    private final InventoryComponent inventoryComponent;

    public InventoryQueryDecorator(
            final CommandService<Create, CreateResult> decoratee,
            final InventoryComponent inventoryComponent
    ) {
        this.decoratee = decoratee;
        this.inventoryComponent = inventoryComponent;
    }

    @Override
    public CreateResult execute(Create command) {
        final List<InventoryQuery> inventoryQueries = command.getItems().stream().map(item -> {
            return new InventoryQuery(item.getProductId(), item.getName(), item.getQuantity());
        }).collect(Collectors.toList());

        final InventoryQueryResult result = inventoryComponent.query(inventoryQueries);
        if (result.getOutOfStockQueries().size() > 0) {
            String names = result.getOutOfStockQueries().stream().map(q -> q.getProductName()).collect(Collectors.joining(", "));
            throw new RuntimeException("products out of stock: " + names);
        }
        return decoratee.execute(command);
    }
}
