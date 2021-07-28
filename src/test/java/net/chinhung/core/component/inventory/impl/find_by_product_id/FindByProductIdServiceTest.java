package net.chinhung.core.component.inventory.impl.find_by_product_id;

import net.chinhung.core.component.inventory.impl.CoreInventoryDataSource;
import net.chinhung.core.component.inventory.FindByProductId;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FindByProductIdServiceTest {

    @Test
    public void testExecute() {
        final CoreInventoryDataSource dataSource = Mockito.mock(CoreInventoryDataSource.class);
        final FindByProductIdService findByIdService = new FindByProductIdService(dataSource);

        final FindByProductId query = Mockito.any();
        findByIdService.execute(query);

        Mockito.verify(dataSource).findByProductId(query);
    }
}
