package net.chinhung.core.component.inventory.impl.update;

import net.chinhung.core.component.inventory.impl.CoreInventoryDataSource;
import net.chinhung.core.component.inventory.Update;
import net.chinhung.core.component.inventory.UpdateResult;
import net.chinhung.fundamental.aspect.CommandService;

public class UpdateService implements CommandService<Update, UpdateResult> {

    private final CoreInventoryDataSource dataSource;

    public UpdateService(final CoreInventoryDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public UpdateResult execute(final Update command) {
        return dataSource.update(command);
    }
}
