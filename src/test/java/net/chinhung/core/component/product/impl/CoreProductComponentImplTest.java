package net.chinhung.core.component.product.impl;

import net.chinhung.core.component.product.Create;
import net.chinhung.core.component.product.CreateResult;
import net.chinhung.core.component.product.Delete;
import net.chinhung.core.component.product.DeleteResult;
import net.chinhung.core.component.product.GetAll;
import net.chinhung.core.component.product.GetAllResult;
import net.chinhung.core.component.product.Update;
import net.chinhung.core.component.product.UpdateResult;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CoreProductComponentImplTest {

    @Mock private QueryService<GetAll, GetAllResult> getAllService;
    @Mock private CommandService<Create, CreateResult> createService;
    @Mock private CommandService<Delete, DeleteResult> deleteService;
    @Mock private CommandService<Update, UpdateResult> updateService;
    private CoreProductComponentImpl instance;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        instance = new CoreProductComponentImpl(getAllService, createService, deleteService, updateService);
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
