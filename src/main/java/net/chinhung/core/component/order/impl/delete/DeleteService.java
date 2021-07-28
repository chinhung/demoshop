package net.chinhung.core.component.order.impl.delete;

import net.chinhung.core.component.order.impl.CoreOrderDataSource;
import net.chinhung.core.component.order.Delete;
import net.chinhung.core.component.order.DeleteResult;
import net.chinhung.fundamental.aspect.CommandService;

public class DeleteService implements CommandService<Delete, DeleteResult> {

    private final CoreOrderDataSource dataSource;

    public DeleteService(final CoreOrderDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public DeleteResult execute(final Delete command) {
        return dataSource.delete(command);
    }
}
