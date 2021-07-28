package net.chinhung.application.component.order.impl.create;

import net.chinhung.application.component.order.Create;
import net.chinhung.application.component.order.CreateResult;
import net.chinhung.application.component.inventory.InventoryComponent;
import net.chinhung.application.component.inventory.InventoryQuery;
import net.chinhung.application.component.inventory.InventoryQueryResult;
import net.chinhung.application.order.Item;
import net.chinhung.core.order.CoreItem;
import net.chinhung.fundamental.aspect.CommandService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static util.TestUtil.ANY_INT;
import static util.TestUtil.ANY_STRING;

public class InventoryQueryDecoratorTest {

    @Mock private InventoryComponent inventoryComponent;
    @Mock private CommandService<Create, CreateResult> decoratee;

    private InventoryQuery anyInventoryQuery;
    private InventoryQueryResult queryResult;
    private InventoryQueryDecorator decorator;
    private Create anyCommand;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        anyInventoryQuery = new InventoryQuery(ANY_STRING, ANY_STRING, ANY_INT);
        queryResult = new InventoryQueryResult();
        decorator = new InventoryQueryDecorator(decoratee, inventoryComponent);
        anyCommand = new Create(Collections.singletonList(new Item(new CoreItem(ANY_STRING, ANY_STRING, ANY_INT))));
    }

    @Test
    public void testExecute_InStock() {
        queryResult.inStock(anyInventoryQuery);
        Mockito.when(inventoryComponent.query(Mockito.any())).thenReturn(queryResult);

        decorator.execute(anyCommand);

        Mockito.verify(decoratee).execute(Mockito.any());
    }

    @Test
    public void testExecute_OutOfStock() {
        queryResult.outOfStock(anyInventoryQuery);
        Mockito.when(inventoryComponent.query(Mockito.any())).thenReturn(queryResult);

        Assertions.assertThrows(RuntimeException.class, () -> {
            decorator.execute(anyCommand);
        });
    }
}
