package net.chinhung.core.component.product.impl.update;

import net.chinhung.core.component.product.Update;
import net.chinhung.core.component.product.impl.CoreProductDataSource;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UpdateServiceTest {

    @Test
    public void testExecute() {
        final CoreProductDataSource dataSource = Mockito.mock(CoreProductDataSource.class);
        final UpdateService updateService = new UpdateService(dataSource);

        final Update command = Mockito.any();
        updateService.execute(command);

        Mockito.verify(dataSource).update(command);
    }
}
