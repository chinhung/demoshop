package net.chinhung.core.component.product.impl.delete;

import net.chinhung.core.component.product.Delete;
import net.chinhung.core.component.product.impl.CoreProductDataSource;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DeleteServiceTest {

    @Test
    public void testExecute() {
        final CoreProductDataSource dataSource = Mockito.mock(CoreProductDataSource.class);
        final DeleteService deleteService = new DeleteService(dataSource);

        final Delete command = Mockito.any();
        deleteService.execute(command);

        Mockito.verify(dataSource).delete(command);
    }
}
