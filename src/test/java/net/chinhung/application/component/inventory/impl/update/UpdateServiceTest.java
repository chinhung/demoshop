package net.chinhung.application.component.inventory.impl.update;

import net.chinhung.application.component.inventory.Update;
import net.chinhung.core.inventory.CoreInventoryRecord;
import net.chinhung.core.component.inventory.CoreInventoryComponent;
import net.chinhung.core.component.inventory.UpdateResult;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static util.TestUtil.ANY_INT;
import static util.TestUtil.ANY_STRING;

public class UpdateServiceTest {

    @Test
    public void testExecute() {
        final CoreInventoryComponent coreInventoryComponent = Mockito.mock(CoreInventoryComponent.class);
        final UpdateResult anyResult = new UpdateResult(new CoreInventoryRecord(ANY_STRING, ANY_STRING, ANY_STRING, ANY_INT));
        Mockito.when(coreInventoryComponent.update(Mockito.any())).thenReturn(anyResult);
        final UpdateService updateService = new UpdateService(coreInventoryComponent);

        final Update anyCommand = new Update(ANY_STRING, ANY_INT);
        updateService.execute(anyCommand);

        Mockito.verify(coreInventoryComponent).update(Mockito.any());
    }
}
