package net.chinhung.core.component.product.impl.get_all;

import net.chinhung.core.component.product.GetAll;
import net.chinhung.core.component.product.impl.CoreProductDataSource;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GetAllServiceTest {

    @Test
    public void testExecute() {
        final CoreProductDataSource dataSource = Mockito.mock(CoreProductDataSource.class);
        final GetAllService getAllService = new GetAllService(dataSource);

        final GetAll query = Mockito.any();
        getAllService.execute(query);

        Mockito.verify(dataSource).getAll(query);
    }
}
