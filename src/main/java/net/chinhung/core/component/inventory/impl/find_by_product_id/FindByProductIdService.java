package net.chinhung.core.component.inventory.impl.find_by_product_id;

import net.chinhung.core.component.inventory.impl.CoreInventoryDataSource;
import net.chinhung.core.component.inventory.FindByProductId;
import net.chinhung.core.component.inventory.FindByProductIdResult;
import net.chinhung.fundamental.aspect.QueryService;

public class FindByProductIdService implements QueryService<FindByProductId, FindByProductIdResult> {

    private final CoreInventoryDataSource dataSource;

    public FindByProductIdService(final CoreInventoryDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public FindByProductIdResult execute(final FindByProductId query) {
        return dataSource.findByProductId(query);
    }
}
