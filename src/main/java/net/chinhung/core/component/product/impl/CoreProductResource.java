package net.chinhung.core.component.product.impl;

public class CoreProductResource {

    private final CoreProductDataSource dataSource;

    public CoreProductResource(final CoreProductDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public CoreProductDataSource coreProductDataSource() {
        return dataSource;
    }
}
