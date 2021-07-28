package net.chinhung.application.product;

import net.chinhung.core.product.CoreProduct;

public class Product {

    private final CoreProduct coreProduct;

    private final StockQuantity stockQuantity;

    public Product(CoreProduct coreProduct, StockQuantity stockQuantity) {
        this.coreProduct = coreProduct;
        this.stockQuantity = stockQuantity;
    }

    public String getId() {
        return coreProduct.getId();
    }

    public String getName() {
        return coreProduct.getName();
    }

    public Integer getPrice() {
        return coreProduct.getPrice();
    }

    public StockQuantity getStockQuantity() {
        return stockQuantity;
    }
}
