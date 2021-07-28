package net.chinhung.application.product.lazy_stock_quantity;

import net.chinhung.application.product.StockQuantity;

class LazyStockQuantity implements StockQuantity {

    private final String productId;
    private final StockQuantityStore stockQuantityStore;

    public LazyStockQuantity(String productId, StockQuantityStore stockQuantityStore) {
        this.productId = productId;
        this.stockQuantityStore = stockQuantityStore;
    }

    @Override
    public Integer getValue() {
        return stockQuantityStore.findByProductId(productId);
    }
}
