package net.chinhung.application.product.lazy_stock_quantity;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LazyStockQuantityTest {

    @Test
    public void testGetValue() {
        final StockQuantityStore store = Mockito.mock(StockQuantityStore.class);
        final LazyStockQuantity lazyStockQuantity = new LazyStockQuantity(Mockito.anyString(), store);

        lazyStockQuantity.getValue();

        Mockito.verify(store).findByProductId(Mockito.anyString());
    }
}
