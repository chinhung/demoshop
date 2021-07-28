package net.chinhung.core.component.product.impl.update;

import net.chinhung.core.component.product.Update;
import net.chinhung.core.component.product.UpdateResult;
import net.chinhung.core.component.product.impl.CoreProductDataSource;
import net.chinhung.fundamental.aspect.CommandService;

public class UpdateService implements CommandService<Update, UpdateResult> {

    private final CoreProductDataSource dataSource;

    public UpdateService(final CoreProductDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public UpdateResult execute(final Update command) {
        return dataSource.update(command);
    }
}
