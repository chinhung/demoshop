package net.chinhung.core.component.order.impl.get_all;

import net.chinhung.core.component.order.impl.CoreOrderDataSource;
import net.chinhung.core.component.order.GetAll;
import net.chinhung.core.component.order.GetAllResult;
import net.chinhung.fundamental.aspect.QueryService;

public class GetAllService implements QueryService<GetAll, GetAllResult> {

    private final CoreOrderDataSource dataSource;

    public GetAllService(final CoreOrderDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public GetAllResult execute(final GetAll command) {
        return dataSource.getAll(command);
    }
}
