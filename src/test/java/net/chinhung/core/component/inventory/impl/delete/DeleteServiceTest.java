package net.chinhung.core.component.inventory.impl.delete;

import net.chinhung.core.component.inventory.impl.CoreInventoryDataSource;
import net.chinhung.core.component.inventory.Delete;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DeleteServiceTest {

    @Test
    public void testExecute() {
        final CoreInventoryDataSource dataSource = Mockito.mock(CoreInventoryDataSource.class);
        final DeleteService deleteService = new DeleteService(dataSource);

        final Delete command = Mockito.any();
        deleteService.execute(command);

        Mockito.verify(dataSource).delete(command);
    }
}
