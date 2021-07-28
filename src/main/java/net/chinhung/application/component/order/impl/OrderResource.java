package net.chinhung.application.component.order.impl;

import net.chinhung.application.component.inventory.InventoryComponent;
import net.chinhung.core.component.order.CoreOrderComponent;

public class OrderResource {

    private final CoreOrderComponent coreOrderComponent;
    private final InventoryComponent inventoryComponent;

    public OrderResource(CoreOrderComponent coreOrderComponent, InventoryComponent inventoryComponent) {
        this.coreOrderComponent = coreOrderComponent;
        this.inventoryComponent = inventoryComponent;
    }

    public CoreOrderComponent coreOrderComponent() {
        return coreOrderComponent;
    }

    public InventoryComponent inventoryComponent() {
        return inventoryComponent;
    }
}
