package net.chinhung.core.component.inventory.impl.delete;

import net.chinhung.core.component.inventory.impl.CoreInventoryDataSource;
import net.chinhung.core.component.inventory.Delete;
import net.chinhung.core.component.inventory.DeleteResult;
import net.chinhung.fundamental.aspect.CommandService;

public class DeleteService implements CommandService<Delete, DeleteResult> {

    private final CoreInventoryDataSource dataSource;

    public DeleteService(final CoreInventoryDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public DeleteResult execute(final Delete command) {
        return dataSource.delete(command);
    }
}
