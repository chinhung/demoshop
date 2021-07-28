package net.chinhung.application.component.inventory.impl;

import net.chinhung.core.component.inventory.CoreInventoryComponent;

public class InventoryResource {

    private final CoreInventoryComponent coreInventoryComponent;

    public InventoryResource(final CoreInventoryComponent coreInventoryComponent) {
        this.coreInventoryComponent = coreInventoryComponent;
    }

    public CoreInventoryComponent coreInventoryComponent() {
        return coreInventoryComponent;
    }
}
