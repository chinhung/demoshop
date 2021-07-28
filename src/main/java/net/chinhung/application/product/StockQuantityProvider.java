package net.chinhung.application.product;

import java.util.List;

public interface StockQuantityProvider {

    StockQuantity provide(String productId, List<String> productIdGroup);

    StockQuantity provide(String productId);
}
