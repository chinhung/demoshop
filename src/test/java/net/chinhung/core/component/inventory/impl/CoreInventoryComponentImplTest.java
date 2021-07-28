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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CoreInventoryComponentImplTest {

    @Mock private QueryService<GetAll, GetAllResult> getAllService;
    @Mock private QueryService<FindByProductId, FindByProductIdResult> findByProductIdService;
    @Mock private CommandService<Create, CreateResult> createService;
    @Mock private CommandService<Delete, DeleteResult> deleteService;
    @Mock private CommandService<Update, UpdateResult> updateService;
    private CoreInventoryComponentImpl instance;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        instance = new CoreInventoryComponentImpl(getAllService, findByProductIdService, createService, deleteService, updateService);
    }

    @Test
    public void findByProductIdService() {
        final FindByProductId query = Mockito.any();
        instance.findByProductId(query);

        Mockito.verify(findByProductIdService).execute(query);
    }

    @Test
    public void getAllService() {
        final GetAll query = Mockito.any();
        instance.getAll(query);

        Mockito.verify(getAllService).execute(query);
    }

    @Test
    public void createService() {
        final Create command = Mockito.any();
        instance.create(command);

        Mockito.verify(createService).execute(command);
    }

    @Test
    public void deleteService() {
        final Delete command = Mockito.any();
        instance.delete(command);

        Mockito.verify(deleteService).execute(command);
    }

    @Test
    public void updateService() {
        final Update command = Mockito.any();
        instance.update(command);

        Mockito.verify(updateService).execute(command);
    }
}
