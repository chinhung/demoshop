package net.chinhung.application.component.product.impl;

import net.chinhung.application.component.inventory.InventoryComponent;
import net.chinhung.core.component.product.CoreProductComponent;

public class ProductResource {

    private final CoreProductComponent coreProductComponent;

    private final InventoryComponent inventoryComponent;

    public ProductResource(
            final CoreProductComponent coreProductComponent,
            final InventoryComponent inventoryComponent
    ) {
        this.coreProductComponent = coreProductComponent;
        this.inventoryComponent = inventoryComponent;
    }

    public CoreProductComponent coreProductComponent() {
        return coreProductComponent;
    }

    public InventoryComponent inventoryComponent() {
        return inventoryComponent;
    }
}
