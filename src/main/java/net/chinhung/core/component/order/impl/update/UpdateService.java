package net.chinhung.core.component.order.impl.update;

import net.chinhung.core.component.order.impl.CoreOrderDataSource;
import net.chinhung.core.component.order.Update;
import net.chinhung.core.component.order.UpdateResult;
import net.chinhung.fundamental.aspect.CommandService;

public class UpdateService implements CommandService<Update, UpdateResult> {

    private final CoreOrderDataSource dataSource;

    public UpdateService(final CoreOrderDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public UpdateResult execute(final Update command) {
        return dataSource.update(command);
    }
}
