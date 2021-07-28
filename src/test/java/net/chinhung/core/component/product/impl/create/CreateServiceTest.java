package net.chinhung.core.component.product.impl.create;

import net.chinhung.core.component.product.Create;
import net.chinhung.core.component.product.impl.CoreProductDataSource;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CreateServiceTest {

    @Test
    public void testExecute() {
        final CoreProductDataSource dataSource = Mockito.mock(CoreProductDataSource.class, "dataSource");
        final CreateService createService = new CreateService(dataSource);

        final Create command = Mockito.any();
        createService.execute(command);

        Mockito.verify(dataSource).create(command);
    }
}
