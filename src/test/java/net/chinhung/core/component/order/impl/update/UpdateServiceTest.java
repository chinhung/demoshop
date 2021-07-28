package net.chinhung.core.component.order.impl.update;

import net.chinhung.core.component.order.impl.CoreOrderDataSource;
import net.chinhung.core.component.order.Update;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UpdateServiceTest {

    @Test
    public void testExecute() {
        final CoreOrderDataSource dataSource = Mockito.mock(CoreOrderDataSource.class);
        final UpdateService updateService = new UpdateService(dataSource);

        final Update command = Mockito.any();
        updateService.execute(command);

        Mockito.verify(dataSource).update(command);
    }
}
