package net.chinhung.application.component.order.impl.find_by_id;

import net.chinhung.application.component.order.FindById;
import net.chinhung.core.component.order.CoreOrderComponent;
import net.chinhung.core.component.order.FindByIdResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static util.TestUtil.ANY_STRING;

public class FindByIdServiceTest {

    @Mock private CoreOrderComponent coreOrderComponent;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        final FindByIdResult anyResult = new FindByIdResult(Optional.empty());
        Mockito.when(coreOrderComponent.findById(Mockito.any())).thenReturn(anyResult);
        final FindByIdService findByIdService = new FindByIdService(coreOrderComponent);

        final FindById anyQuery = new FindById(ANY_STRING);
        findByIdService.execute(anyQuery);

        Mockito.verify(coreOrderComponent).findById(Mockito.any());
    }
}
