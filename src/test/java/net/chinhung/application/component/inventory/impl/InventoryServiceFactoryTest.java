package net.chinhung.application.component.inventory.impl;

import net.chinhung.application.component.inventory.GetAll;
import net.chinhung.application.component.inventory.GetAllResult;
import net.chinhung.application.component.inventory.Update;
import net.chinhung.application.component.inventory.UpdateResult;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;
import net.chinhung.fundamental.aspect.SpyDecorator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class InventoryServiceFactoryTest {

    private SpyDecorator decorateWithActionValidatorSpy;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        decorateWithActionValidatorSpy = new SpyDecorator();
    }

    @Test
    public void getAllService() {
        final QueryService<GetAll, GetAllResult> implementer = Mockito.mock(QueryService.class);
        final QueryService<GetAll, GetAllResult> getAllService = InventoryServiceFactory.getAllService(implementer);

        getAllService.execute(Mockito.any());

        Mockito.verify(implementer).execute(Mockito.any());
    }

    @Test
    public void updateService() {
        final CommandService<Update, UpdateResult> implementer = Mockito.mock(CommandService.class);
        final CommandService<Update, UpdateResult> updateService = InventoryServiceFactory.updateService(implementer, c -> decorateWithActionValidatorSpy.setDecratee(c));

        updateService.execute(Mockito.any());

        Assertions.assertTrue(decorateWithActionValidatorSpy.executeIsCalled);
        Mockito.verify(implementer).execute(Mockito.any());
    }
}
