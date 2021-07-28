package net.chinhung.application.component.order.impl.get_all;

import net.chinhung.application.component.order.GetAll;
import net.chinhung.application.component.order.GetAllResult;
import net.chinhung.core.component.order.CoreOrderComponent;
import net.chinhung.fundamental.aspect.QueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

public class GetAllServiceTest {

    @Mock private CoreOrderComponent coreOrderComponent;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        final net.chinhung.core.component.order.GetAllResult anyResult = new net.chinhung.core.component.order.GetAllResult(new ArrayList<>());
        Mockito.when(coreOrderComponent.getAll(Mockito.any())).thenReturn(anyResult);
        final QueryService<GetAll, GetAllResult> getAllService = new GetAllService(coreOrderComponent);

        getAllService.execute(Mockito.any());

        Mockito.verify(coreOrderComponent).getAll(Mockito.any());
    }
}
