package net.chinhung.application.component.inventory.impl.get_all;

import net.chinhung.core.component.inventory.CoreInventoryComponent;
import net.chinhung.core.component.inventory.GetAllResult;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class GetAllServiceTest {

    @Test
    public void testExecute() {
        final CoreInventoryComponent coreInventoryComponent = Mockito.mock(CoreInventoryComponent.class);
        final GetAllResult anyResult = new GetAllResult(new ArrayList<>());
        Mockito.when(coreInventoryComponent.getAll(Mockito.any())).thenReturn(anyResult);
        final GetAllService getAllService = new GetAllService(coreInventoryComponent);

        getAllService.execute(Mockito.any());

        Mockito.verify(coreInventoryComponent).getAll(Mockito.any());
    }
}
