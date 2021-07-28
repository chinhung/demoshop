package net.chinhung.core.component.order.impl.create;

import net.chinhung.core.component.order.impl.CoreOrderDataSource;
import net.chinhung.core.component.order.Create;
import net.chinhung.core.component.order.CreateResult;
import net.chinhung.fundamental.aspect.CommandService;

public class CreateService implements CommandService<Create, CreateResult> {

    private final CoreOrderDataSource dataSource;

    public CreateService(final CoreOrderDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public CreateResult execute(final Create command) {
        return dataSource.create(command);
    }
}
