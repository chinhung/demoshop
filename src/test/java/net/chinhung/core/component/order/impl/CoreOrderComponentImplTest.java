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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CoreOrderComponentImplTest {

    @Mock private QueryService<GetAll, GetAllResult> getAllService;
    @Mock private QueryService<FindById, FindByIdResult> findByIdService;
    @Mock private CommandService<Create, CreateResult> createService;
    @Mock private CommandService<Delete, DeleteResult> deleteService;
    @Mock private CommandService<Update, UpdateResult> updateService;
    private CoreOrderComponentImpl instance;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        instance = new CoreOrderComponentImpl(getAllService, findByIdService, createService, deleteService, updateService);
    }

    @Test
    public void findByIdService() {
        final FindById query = Mockito.any();
        instance.findById(query);

        Mockito.verify(findByIdService).execute(query);
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
