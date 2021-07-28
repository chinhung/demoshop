package net.chinhung.core.component.product.impl.create;

import net.chinhung.core.component.product.Create;
import net.chinhung.core.component.product.CreateResult;
import net.chinhung.core.component.product.impl.CoreProductDataSource;
import net.chinhung.fundamental.aspect.CommandService;

public class CreateService implements CommandService<Create, CreateResult> {

    private final CoreProductDataSource dataSource;

    public CreateService(final CoreProductDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public CreateResult execute(final Create command) {
        return dataSource.create(command);
    }
}
