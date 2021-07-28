package net.chinhung.core.component.inventory.impl.get_all;

import net.chinhung.core.component.inventory.impl.CoreInventoryDataSource;
import net.chinhung.core.component.inventory.GetAll;
import net.chinhung.core.component.inventory.GetAllResult;
import net.chinhung.fundamental.aspect.QueryService;

public class GetAllService implements QueryService<GetAll, GetAllResult> {

    private final CoreInventoryDataSource dataSource;

    public GetAllService(final CoreInventoryDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public GetAllResult execute(final GetAll query) {
        return dataSource.getAll(query);
    }
}
