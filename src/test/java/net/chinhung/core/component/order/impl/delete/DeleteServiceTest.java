package net.chinhung.core.component.order.impl.delete;

import net.chinhung.core.component.order.impl.CoreOrderDataSource;
import net.chinhung.core.component.order.Delete;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DeleteServiceTest {

    @Test
    public void testExecute() {
        final CoreOrderDataSource dataSource = Mockito.mock(CoreOrderDataSource.class);
        final DeleteService deleteService = new DeleteService(dataSource);

        final Delete command = Mockito.any();
        deleteService.execute(command);

        Mockito.verify(dataSource).delete(command);
    }
}
