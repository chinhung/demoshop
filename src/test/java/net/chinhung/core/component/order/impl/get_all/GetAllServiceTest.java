package net.chinhung.core.component.order.impl.get_all;

import net.chinhung.core.component.order.impl.CoreOrderDataSource;
import net.chinhung.core.component.order.GetAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GetAllServiceTest {

    @Test
    public void testExecute() {
        final CoreOrderDataSource dataSource = Mockito.mock(CoreOrderDataSource.class);
        final GetAllService getAllService = new GetAllService(dataSource);

        final GetAll query = Mockito.any();
        getAllService.execute(query);

        Mockito.verify(dataSource).getAll(query);
    }
}
