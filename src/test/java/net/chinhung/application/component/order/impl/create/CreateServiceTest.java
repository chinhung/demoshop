package net.chinhung.application.component.order.impl.create;

import net.chinhung.application.component.order.Create;
import net.chinhung.application.component.order.CreateResult;
import net.chinhung.application.order.Item;
import net.chinhung.core.order.CoreItem;
import net.chinhung.core.order.CoreOrder;
import net.chinhung.core.order.CoreStatus;
import net.chinhung.core.component.order.CoreOrderComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;

import static util.TestUtil.ANY_INT;
import static util.TestUtil.ANY_STRING;

public class CreateServiceTest {

    @Mock private CoreOrderComponent coreOrderComponent;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        final CreateService createService = new CreateService(coreOrderComponent);
        final Create anyCommand = new Create(Collections.singletonList(new Item(new CoreItem(ANY_STRING, ANY_STRING, ANY_INT))));
        final net.chinhung.core.component.order.CreateResult someResult = new net.chinhung.core.component.order.CreateResult(new CoreOrder("someId", new ArrayList<>(), CoreStatus.APPROVED));
        Mockito.when(coreOrderComponent.create(Mockito.any())).thenReturn(someResult);
        final CreateResult result = createService.execute(anyCommand);

        Mockito.verify(coreOrderComponent).create(Mockito.any());
        Assertions.assertEquals("someId", result.getCreated().getId());
    }
}
