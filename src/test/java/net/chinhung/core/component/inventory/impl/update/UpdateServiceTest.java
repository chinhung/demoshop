package net.chinhung.core.component.inventory.impl.update;

import net.chinhung.core.component.inventory.impl.CoreInventoryDataSource;
import net.chinhung.core.component.inventory.Update;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UpdateServiceTest {

    @Test
    public void testExecute() {
        final CoreInventoryDataSource dataSource = Mockito.mock(CoreInventoryDataSource.class);
        final UpdateService updateService = new UpdateService(dataSource);

        final Update command = Mockito.any();
        updateService.execute(command);

        Mockito.verify(dataSource).update(command);
    }
}
