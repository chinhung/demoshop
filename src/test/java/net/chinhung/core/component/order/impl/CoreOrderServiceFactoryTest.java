package net.chinhung.core.component.order.impl;

import net.chinhung.core.component.order.Create;
import net.chinhung.core.component.order.CreateResult;
import net.chinhung.core.component.order.Delete;
import net.chinhung.core.component.order.DeleteResult;
import net.chinhung.core.component.order.FindById;
import net.chinhung.core.component.order.FindByIdResult;
import net.chinhung.core.component.order.GetAll;
import net.chinhung.core.component.order.GetAllResult;
import net.chinhung.core.component.order.Update;
import net.chinhung.core.component.order.UpdateResult;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;
import net.chinhung.fundamental.aspect.SpyDecorator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CoreOrderServiceFactoryTest {

    @Mock private CoreOrderDataSource dataSource;
    private SpyDecorator decorateWithActionValidatorSpy;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        decorateWithActionValidatorSpy = new SpyDecorator();
    }

    @Test
    public void getAllService() {
        final QueryService<GetAll, GetAllResult> getAllService = CoreOrderServiceFactory.getAllService(dataSource);

        getAllService.execute(Mockito.any());

        Mockito.verify(dataSource).getAll(Mockito.any());
    }

    @Test
    public void findByIdService() {
        final QueryService<FindById, FindByIdResult> findByIdService = CoreOrderServiceFactory.findByIdService(dataSource, c -> decorateWithActionValidatorSpy.setDecratee(c));

        findByIdService.execute(Mockito.any());

        Assertions.assertTrue(decorateWithActionValidatorSpy.executeIsCalled);
        Mockito.verify(dataSource).findById(Mockito.any());
    }

    @Test
    public void createService() {
        final CommandService<Create, CreateResult> createService = CoreOrderServiceFactory.createService(dataSource, c -> decorateWithActionValidatorSpy.setDecratee(c));

        createService.execute(Mockito.any());

        Assertions.assertTrue(decorateWithActionValidatorSpy.executeIsCalled);
        Mockito.verify(dataSource).create(Mockito.any());
    }

    @Test
    public void deleteService() {
        final CommandService<Delete, DeleteResult> deleteService = CoreOrderServiceFactory.deleteService(dataSource, c -> decorateWithActionValidatorSpy.setDecratee(c));

        deleteService.execute(Mockito.any());

        Assertions.assertTrue(decorateWithActionValidatorSpy.executeIsCalled);
        Mockito.verify(dataSource).delete(Mockito.any());
    }

    @Test
    public void updateService() {
        final CommandService<Update, UpdateResult> updateService = CoreOrderServiceFactory.updateService(dataSource, c -> decorateWithActionValidatorSpy.setDecratee(c));

        updateService.execute(Mockito.any());

        Assertions.assertTrue(decorateWithActionValidatorSpy.executeIsCalled);
        Mockito.verify(dataSource).update(Mockito.any());
    }
}
