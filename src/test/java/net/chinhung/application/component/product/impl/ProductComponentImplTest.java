package net.chinhung.application.component.product.impl;

import net.chinhung.application.product.Product;
import net.chinhung.application.component.product.Create;
import net.chinhung.application.component.product.CreateResult;
import net.chinhung.application.component.product.GetAll;
import net.chinhung.application.component.product.GetAllResult;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static util.TestUtil.ANY_STRING;
import static util.TestUtil.ANY_INT;

public class ProductComponentImplTest {

    @Mock private QueryService<GetAll, GetAllResult> getAllService;
    @Mock private CommandService<Create, CreateResult> createService;
    private ProductComponentImpl instance;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        this.instance = new ProductComponentImpl(getAllService, createService);
    }

    @Test
    public void testGetAll() {
        final GetAllResult anyResult = new GetAllResult(new ArrayList<>());
        Mockito.when(getAllService.execute(Mockito.any())).thenReturn(anyResult);

        instance.findProducts();

        Mockito.verify(getAllService).execute(Mockito.any());
    }

    @Test
    public void testCreate() {
        final CreateResult anyResult = new CreateResult(new Product(null, () -> 0));
        Mockito.when(createService.execute(Mockito.any())).thenReturn(anyResult);

        instance.createProduct(ANY_STRING, ANY_INT);

        Mockito.verify(createService).execute(Mockito.any());
    }
}
