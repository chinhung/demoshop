package net.chinhung.core.component.inventory.impl.create;

import net.chinhung.core.component.inventory.impl.CoreInventoryDataSource;
import net.chinhung.core.component.inventory.Create;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CreateServiceTest {

    @Test
    public void testExecute() {
        final CoreInventoryDataSource dataSource = Mockito.mock(CoreInventoryDataSource.class);
        final CreateService createService = new CreateService(dataSource);

        final Create command = Mockito.any();
        createService.execute(command);

        Mockito.verify(dataSource).create(command);
    }
}
