package net.chinhung.application.product.lazy_stock_quantity;

import net.chinhung.application.product.StockQuantity;
import net.chinhung.application.product.StockQuantityProvider;

import java.util.Collections;
import java.util.List;

public class LazyStockQuantityProvider implements StockQuantityProvider {

    private final StockQuantityStoreProvider stockQuantityStoreProvider;

    public LazyStockQuantityProvider(final StockQuantityStoreProvider stockQuantityStoreProvider) {
        this.stockQuantityStoreProvider = stockQuantityStoreProvider;
    }

    @Override
    public StockQuantity provide(String productId, List<String> productIdGroup) {
        return new LazyStockQuantity(productId, stockQuantityStoreProvider.provide(productIdGroup));
    }

    @Override
    public StockQuantity provide(String productId) {
        return provide(productId, Collections.singletonList(productId));
    }
}
