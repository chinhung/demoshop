package net.chinhung.application.component.inventory.impl.update;

import net.chinhung.application.component.inventory.Update;
import net.chinhung.application.component.inventory.UpdateResult;
import net.chinhung.application.inventory.InventoryRecord;
import net.chinhung.core.component.inventory.CoreInventoryComponent;
import net.chinhung.fundamental.aspect.CommandService;

public class UpdateService implements CommandService<Update, UpdateResult> {

    private final CoreInventoryComponent coreInventoryComponent;

    public UpdateService(final CoreInventoryComponent coreInventoryComponent) {
        this.coreInventoryComponent = coreInventoryComponent;
    }

    @Override
    public UpdateResult execute(final Update command) {
        final net.chinhung.core.component.inventory.UpdateResult coreResult = coreInventoryComponent.update(new net.chinhung.core.component.inventory.Update(command.getProductId(), command.getQuantity()));
        return new UpdateResult(new InventoryRecord(coreResult.getUpdated()));
    }

    public static CommandService<Update, UpdateResult> getImplementer(final CoreInventoryComponent coreInventoryComponent) {
        return new UpdateService(coreInventoryComponent);
    }
}
