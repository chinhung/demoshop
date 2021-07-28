package net.chinhung.core.component.inventory.impl.get_all;

import net.chinhung.core.component.inventory.impl.CoreInventoryDataSource;
import net.chinhung.core.component.inventory.GetAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GetAllServiceTest {

    @Test
    public void testExecute() {
        final CoreInventoryDataSource dataSource = Mockito.mock(CoreInventoryDataSource.class);
        final GetAllService getAllService = new GetAllService(dataSource);

        final GetAll query = Mockito.any();
        getAllService.execute(query);

        Mockito.verify(dataSource).getAll(query);
    }
}
