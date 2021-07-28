package net.chinhung.application.product.lazy_stock_quantity;

import net.chinhung.application.component.inventory.InventoryComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class StockQuantityStoreProviderTest {

    @Test
    public void testProvide() throws NoSuchFieldException, IllegalAccessException {
        final InventoryComponent inventoryComponent = Mockito.mock(InventoryComponent.class);
        final StockQuantityStoreProvider provider = new StockQuantityStoreProvider(inventoryComponent);

        final StockQuantityStore store = provider.provide(new ArrayList<>());

        assertThatStoreContainsTheSameInventoryComponent(inventoryComponent, store);
    }

    private void assertThatStoreContainsTheSameInventoryComponent(final InventoryComponent excepted, final StockQuantityStore store)
            throws NoSuchFieldException, IllegalAccessException {

        final Field field = StockQuantityStore.class.getDeclaredField("inventoryComponent");
        field.setAccessible(true);
        final InventoryComponent reflected = (InventoryComponent) field.get(store);
        Assertions.assertSame(excepted, reflected);
    }
}
