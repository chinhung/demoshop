package net.chinhung.platform.resource;

import net.chinhung.application.Resource;
import net.chinhung.core.component.inventory.impl.CoreInventoryDataSource;
import net.chinhung.core.component.order.impl.CoreOrderDataSource;
import net.chinhung.core.component.product.impl.CoreProductDataSource;

public class InMemoryResource implements Resource {

    private final CoreProductDataSource coreProductDataSource;
    private final CoreInventoryDataSource coreInventoryDataSource;
    private final CoreOrderDataSource coreOrderDataSource;

    public InMemoryResource(
            CoreProductDataSource coreProductDataSource,
            CoreInventoryDataSource coreInventoryDataSource,
            CoreOrderDataSource coreorderDataSource
    ) {
        this.coreProductDataSource = coreProductDataSource;
        this.coreInventoryDataSource = coreInventoryDataSource;
        this.coreOrderDataSource = coreorderDataSource;
    }

    @Override
    public CoreProductDataSource coreProductDataSource() {
        return coreProductDataSource;
    }

    @Override
    public CoreInventoryDataSource coreInventoryDataSource() {
        return coreInventoryDataSource;
    }

    @Override
    public CoreOrderDataSource coreOrderDataSource() {
        return coreOrderDataSource;
    }
}
