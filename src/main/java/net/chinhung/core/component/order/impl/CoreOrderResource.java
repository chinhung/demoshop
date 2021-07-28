package net.chinhung.core.component.order.impl;

public class CoreOrderResource {

    private final CoreOrderDataSource dataSource;

    public CoreOrderResource(CoreOrderDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public CoreOrderDataSource coreOrderDataSource() {
        return dataSource;
    }
}
