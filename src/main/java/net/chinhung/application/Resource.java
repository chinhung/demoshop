package net.chinhung.application;

import net.chinhung.core.component.inventory.impl.CoreInventoryDataSource;
import net.chinhung.core.component.order.impl.CoreOrderDataSource;
import net.chinhung.core.component.product.impl.CoreProductDataSource;

public interface Resource {

    CoreProductDataSource coreProductDataSource();

    CoreInventoryDataSource coreInventoryDataSource();

    CoreOrderDataSource coreOrderDataSource();
}
