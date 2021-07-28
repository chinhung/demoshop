package net.chinhung.core.component.product.impl.delete;

import net.chinhung.core.component.product.Delete;
import net.chinhung.core.component.product.DeleteResult;
import net.chinhung.core.component.product.impl.CoreProductDataSource;
import net.chinhung.fundamental.aspect.CommandService;

public class DeleteService implements CommandService<Delete, DeleteResult> {

    private final CoreProductDataSource dataSource;

    public DeleteService(final CoreProductDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public DeleteResult execute(final Delete command) {
        return dataSource.delete(command);
    }
}
