package net.chinhung.core.component.order.impl.create;

import net.chinhung.core.component.order.impl.CoreOrderDataSource;
import net.chinhung.core.component.order.Create;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CreateServiceTest {

    @Test
    public void testExecute() {
        final CoreOrderDataSource dataSource = Mockito.mock(CoreOrderDataSource.class);
        final CreateService createService = new CreateService(dataSource);

        final Create command = Mockito.any();
        createService.execute(command);

        Mockito.verify(dataSource).create(command);
    }
}
