package net.chinhung.application.component.product.impl;

import net.chinhung.application.component.product.Create;
import net.chinhung.application.component.product.CreateResult;
import net.chinhung.application.component.product.GetAll;
import net.chinhung.application.component.product.GetAllResult;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;
import net.chinhung.fundamental.aspect.SpyDecorator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ProductServiceFactoryTest {

    @Mock
    private SpyDecorator decorateWithActionValidatorSpy;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        decorateWithActionValidatorSpy = new SpyDecorator();
    }

    @Test
    public void testGetAll() {
        final QueryService<GetAll, GetAllResult> implementer = Mockito.mock(QueryService.class);
        final QueryService<GetAll, GetAllResult> getAllService = ProductServiceFactory.getAllService(implementer);

        getAllService.execute(Mockito.any());

        Mockito.verify(implementer).execute(Mockito.any());
    }

    @Test
    public void testCreate() {
        final CommandService<Create, CreateResult> implementer = Mockito.mock(CommandService.class);
        final CommandService<Create, CreateResult> createService = ProductServiceFactory.createService(implementer, c -> decorateWithActionValidatorSpy.setDecratee(c));

        createService.execute(Mockito.any());

        Assertions.assertTrue(decorateWithActionValidatorSpy.executeIsCalled);
        Mockito.verify(implementer).execute(Mockito.any());
    }
}
