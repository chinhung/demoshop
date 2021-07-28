package net.chinhung.core.component.product.impl.get_all;

import net.chinhung.core.component.product.GetAll;
import net.chinhung.core.component.product.GetAllResult;
import net.chinhung.core.component.product.impl.CoreProductDataSource;
import net.chinhung.fundamental.aspect.QueryService;

public class GetAllService implements QueryService<GetAll, GetAllResult> {

    private final CoreProductDataSource dataSource;

    public GetAllService(final CoreProductDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public GetAllResult execute(final GetAll command) {
        return dataSource.getAll(command);
    }
}
