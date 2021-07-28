package net.chinhung.application.component.order.impl;

import net.chinhung.application.component.order.Create;
import net.chinhung.application.component.order.CreateResult;
import net.chinhung.application.component.order.GetAll;
import net.chinhung.application.component.order.GetAllResult;
import net.chinhung.application.component.order.FindById;
import net.chinhung.application.component.order.FindByIdResult;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;
import net.chinhung.fundamental.aspect.SpyDecorator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class OrderServiceFactoryTest {

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
        final QueryService<GetAll, GetAllResult> getAllService = OrderServiceFactory.getAllService(implementer);

        getAllService.execute(Mockito.any());

        Mockito.verify(implementer).execute(Mockito.any());
    }

    @Test
    public void testFindById() {
        final QueryService<FindById, FindByIdResult> implementer = Mockito.mock(QueryService.class);
        final QueryService<FindById, FindByIdResult> findByIdService = OrderServiceFactory.findByIdService(implementer, c-> decorateWithActionValidatorSpy.setDecratee(c));

        findByIdService.execute(Mockito.any());

        Assertions.assertTrue(decorateWithActionValidatorSpy.executeIsCalled);
        Mockito.verify(implementer).execute(Mockito.any());
    }

    @Test
    public void testCreate() {
        final SpyDecorator decorateWithInventoryQuerySpy = new SpyDecorator();
        final SpyDecorator decorateWithInventoryUpdateSpy = new SpyDecorator();
        final CommandService<Create, CreateResult> implementer = Mockito.mock(CommandService.class);
        final CommandService<Create, CreateResult> createService = OrderServiceFactory.createService(
                implementer,
                c -> decorateWithInventoryQuerySpy.setDecratee(c),
                c -> decorateWithInventoryUpdateSpy.setDecratee(c),
                c-> decorateWithActionValidatorSpy.setDecratee(c)
        );

        createService.execute(Mockito.any());

        Assertions.assertTrue(decorateWithActionValidatorSpy.executeIsCalled);
        Assertions.assertTrue(decorateWithInventoryUpdateSpy.executeIsCalled);
        Assertions.assertTrue(decorateWithInventoryQuerySpy.executeIsCalled);
        Mockito.verify(implementer).execute(Mockito.any());
    }
}
