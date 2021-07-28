package net.chinhung.core.component.inventory.impl.create;

import net.chinhung.core.component.inventory.impl.CoreInventoryDataSource;
import net.chinhung.core.component.inventory.Create;
import net.chinhung.core.component.inventory.CreateResult;
import net.chinhung.fundamental.aspect.CommandService;

public class CreateService implements CommandService<Create, CreateResult> {

    private final CoreInventoryDataSource dataSource;

    public CreateService(final CoreInventoryDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public CreateResult execute(final Create command) {
        return dataSource.create(command);
    }
}
