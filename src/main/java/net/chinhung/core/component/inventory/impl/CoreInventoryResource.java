package net.chinhung.core.component.inventory.impl;

public class CoreInventoryResource {

    private final CoreInventoryDataSource coreInventoryDataSource;

    public CoreInventoryResource(CoreInventoryDataSource coreInventoryDataSource) {
        this.coreInventoryDataSource = coreInventoryDataSource;
    }

    public CoreInventoryDataSource coreInventoryDataSource() {
        return coreInventoryDataSource;
    }
}
