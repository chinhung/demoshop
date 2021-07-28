package net.chinhung.core.component.product.impl;

import net.chinhung.core.component.product.*;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;
import net.chinhung.fundamental.aspect.SpyDecorator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CoreProductServiceFactoryTest {

    @Mock private CoreProductDataSource dataSource;
    private SpyDecorator decorateWithActionValidatorSpy;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        decorateWithActionValidatorSpy = new SpyDecorator();
    }

    @Test
    public void getAllService() {
        final QueryService<GetAll, GetAllResult> getAllService = CoreProductServiceFactory.getAllService(dataSource);

        getAllService.execute(Mockito.any());

        Mockito.verify(dataSource).getAll(Mockito.any());
    }

    @Test
    public void createService() {
        final CommandService<Create, CreateResult> createService = CoreProductServiceFactory.createService(dataSource, c -> decorateWithActionValidatorSpy.setDecratee(c));

        createService.execute(Mockito.any());

        Assertions.assertTrue(decorateWithActionValidatorSpy.executeIsCalled);
        Mockito.verify(dataSource).create(Mockito.any());
    }

    @Test
    public void deleteService() {
        final CommandService<Delete, DeleteResult> deleteService = CoreProductServiceFactory.deleteService(dataSource, c -> decorateWithActionValidatorSpy.setDecratee(c));

        deleteService.execute(Mockito.any());

        Assertions.assertTrue(decorateWithActionValidatorSpy.executeIsCalled);
        Mockito.verify(dataSource).delete(Mockito.any());
    }

    @Test
    public void updateService() {
        final CommandService<Update, UpdateResult> updateService = CoreProductServiceFactory.updateService(dataSource, c -> decorateWithActionValidatorSpy.setDecratee(c));

        updateService.execute(Mockito.any());

        Assertions.assertTrue(decorateWithActionValidatorSpy.executeIsCalled);
        Mockito.verify(dataSource).update(Mockito.any());
    }
}
