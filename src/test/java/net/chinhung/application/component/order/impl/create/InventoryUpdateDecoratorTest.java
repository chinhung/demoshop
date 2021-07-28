package net.chinhung.application.component.order.impl.create;

import net.chinhung.application.component.order.Create;
import net.chinhung.application.component.order.CreateResult;
import net.chinhung.application.inventory.InventoryRecord;
import net.chinhung.application.component.inventory.InventoryComponent;
import net.chinhung.application.order.Item;
import net.chinhung.core.inventory.CoreInventoryRecord;
import net.chinhung.core.order.CoreItem;
import net.chinhung.fundamental.aspect.CommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.stream.Stream;

import static util.TestUtil.ANY_STRING;

public class InventoryUpdateDecoratorTest {

    @Mock private InventoryComponent inventoryComponent;
    @Mock private CommandService<Create, CreateResult> decoratee;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        final Integer originStockQuantity = 1;
        final Integer requiredQuantityInCommand = 1;
        final InventoryUpdateDecorator decorator = new InventoryUpdateDecorator(decoratee, inventoryComponent);
        Mockito.when(inventoryComponent.getInventoryRecords(Mockito.any()))
                .thenReturn(Stream.of(new InventoryRecord(new CoreInventoryRecord(ANY_STRING, ANY_STRING, "someProductId", originStockQuantity))));

        final Create someCommand = new Create(Collections.singletonList(new Item(new CoreItem(ANY_STRING, "someProductId", requiredQuantityInCommand))));
        decorator.execute(someCommand);

        Mockito.verify(inventoryComponent).update("someProductId", originStockQuantity - requiredQuantityInCommand);
        Mockito.verify(decoratee).execute(Mockito.any());
    }

}
