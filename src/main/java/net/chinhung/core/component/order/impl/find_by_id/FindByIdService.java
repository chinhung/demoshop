package net.chinhung.core.component.order.impl.find_by_id;

import net.chinhung.core.component.order.impl.CoreOrderDataSource;
import net.chinhung.core.component.order.FindById;
import net.chinhung.core.component.order.FindByIdResult;
import net.chinhung.fundamental.aspect.QueryService;

public class FindByIdService implements QueryService<FindById, FindByIdResult> {

    private final CoreOrderDataSource dataSource;

    public FindByIdService(final CoreOrderDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public FindByIdResult execute(final FindById command) {
        return dataSource.findById(command);
    }
}
