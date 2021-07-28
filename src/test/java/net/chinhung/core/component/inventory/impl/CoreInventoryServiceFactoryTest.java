package net.chinhung.core.component.inventory.impl;

import net.chinhung.core.component.inventory.Create;
import net.chinhung.core.component.inventory.CreateResult;
import net.chinhung.core.component.inventory.Delete;
import net.chinhung.core.component.inventory.DeleteResult;
import net.chinhung.core.component.inventory.FindByProductId;
import net.chinhung.core.component.inventory.FindByProductIdResult;
import net.chinhung.core.component.inventory.GetAll;
import net.chinhung.core.component.inventory.GetAllResult;
import net.chinhung.core.component.inventory.Update;
import net.chinhung.core.component.inventory.UpdateResult;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;
import net.chinhung.fundamental.aspect.SpyDecorator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CoreInventoryServiceFactoryTest {

    @Mock private CoreInventoryDataSource dataSource;
    private SpyDecorator decorateWithActionValidatorSpy;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        decorateWithActionValidatorSpy = new SpyDecorator();
    }

    @Test
    public void getAllService() {
        final QueryService<GetAll, GetAllResult> getAllService = CoreInventoryServiceFactory.getAllService(dataSource);

        getAllService.execute(Mockito.any());

        Mockito.verify(dataSource).getAll(Mockito.any());
    }

    @Test
    public void findByIdService() {
        final QueryService<FindByProductId, FindByProductIdResult> findByIdService = CoreInventoryServiceFactory.findByProductIdService(dataSource, c -> decorateWithActionValidatorSpy.setDecratee(c));

        findByIdService.execute(Mockito.any());

        Assertions.assertTrue(decorateWithActionValidatorSpy.executeIsCalled);
        Mockito.verify(dataSource).findByProductId(Mockito.any());
    }

    @Test
    public void createService() {
        final CommandService<Create, CreateResult> createService = CoreInventoryServiceFactory.createService(dataSource, c -> decorateWithActionValidatorSpy.setDecratee(c));

        createService.execute(Mockito.any());

        Assertions.assertTrue(decorateWithActionValidatorSpy.executeIsCalled);
        Mockito.verify(dataSource).create(Mockito.any());
    }

    @Test
    public void deleteService() {
        final CommandService<Delete, DeleteResult> deleteService = CoreInventoryServiceFactory.deleteService(dataSource, c -> decorateWithActionValidatorSpy.setDecratee(c));

        deleteService.execute(Mockito.any());

        Assertions.assertTrue(decorateWithActionValidatorSpy.executeIsCalled);
        Mockito.verify(dataSource).delete(Mockito.any());
    }

    @Test
    public void updateService() {
        final CommandService<Update, UpdateResult> updateService = CoreInventoryServiceFactory.updateService(dataSource, c -> decorateWithActionValidatorSpy.setDecratee(c));

        updateService.execute(Mockito.any());

        Assertions.assertTrue(decorateWithActionValidatorSpy.executeIsCalled);
        Mockito.verify(dataSource).update(Mockito.any());
    }
}
