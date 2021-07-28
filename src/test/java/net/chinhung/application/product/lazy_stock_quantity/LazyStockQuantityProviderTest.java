package net.chinhung.application.product.lazy_stock_quantity;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static util.TestUtil.ANY_STRING;

public class LazyStockQuantityProviderTest {

    @Test
    public void testProvide() {
        final StockQuantityStoreProvider storeProvider = Mockito.mock(StockQuantityStoreProvider.class);
        final LazyStockQuantityProvider provider = new LazyStockQuantityProvider(storeProvider);

        provider.provide(ANY_STRING, Mockito.any());

        Mockito.verify(storeProvider).provide(Mockito.any());
    }
}
