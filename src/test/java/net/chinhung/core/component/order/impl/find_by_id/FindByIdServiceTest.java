package net.chinhung.core.component.order.impl.find_by_id;

import net.chinhung.core.component.order.impl.CoreOrderDataSource;
import net.chinhung.core.component.order.FindById;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FindByIdServiceTest {

    @Test
    public void testExecute() {
        final CoreOrderDataSource dataSource = Mockito.mock(CoreOrderDataSource.class);
        final FindByIdService findByIdService = new FindByIdService(dataSource);

        final FindById query = Mockito.any();
        findByIdService.execute(query);

        Mockito.verify(dataSource).findById(query);
    }
}
