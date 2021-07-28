package net.chinhung.application.component.order.impl.create;

import net.chinhung.application.component.order.Create;
import net.chinhung.application.component.order.CreateResult;
import net.chinhung.application.inventory.InventoryRecord;
import net.chinhung.application.component.inventory.InventoryComponent;
import net.chinhung.application.order.Item;
import net.chinhung.fundamental.aspect.CommandService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InventoryUpdateDecorator implements CommandService<Create, CreateResult> {

    private final CommandService<Create, CreateResult> decoratee;
    private final InventoryComponent inventoryComponent;

    public InventoryUpdateDecorator(
            final CommandService<Create, CreateResult> decoratee,
            final InventoryComponent inventoryComponent
    ) {
        this.decoratee = decoratee;
        this.inventoryComponent = inventoryComponent;
    }

    @Override
    public CreateResult execute(Create command) {
        final CreateResult result = decoratee.execute(command);
        final Stream<InventoryRecord> toUpdate = inventoryComponent.getInventoryRecords(command.getItems().stream().map(item -> item.getProductId()).collect(Collectors.toList()));
        toUpdate.forEach(inventoryRecord -> {
            Optional<Item> required = command.getItems().stream().filter(item -> item.getProductId().equals(inventoryRecord.getProductId())).findFirst();
            inventoryComponent.update(inventoryRecord.getProductId(), inventoryRecord.getStockQuantity() - required.get().getQuantity());
        });
        return result;
    }
}
