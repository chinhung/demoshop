package net.chinhung.application.component.product.impl.get_all;

import net.chinhung.application.component.product.GetAll;
import net.chinhung.application.component.product.GetAllResult;
import net.chinhung.application.product.StockQuantityProvider;
import net.chinhung.core.product.CoreProduct;
import net.chinhung.core.component.product.CoreProductComponent;
import net.chinhung.fundamental.aspect.QueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static util.TestUtil.ANY_INT;
import static util.TestUtil.ANY_STRING;

public class GetAllServiceTest {

    @Mock private CoreProductComponent coreProductComponent;
    @Mock private StockQuantityProvider stockQuantityProvider;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        final net.chinhung.core.component.product.GetAllResult anyResult = new net.chinhung.core.component.product.GetAllResult(Collections.singletonList(new CoreProduct(ANY_STRING, ANY_STRING, ANY_INT)));
        Mockito.when(coreProductComponent.getAll(Mockito.any())).thenReturn(anyResult);
        final QueryService<GetAll, GetAllResult> getAllService = new GetAllService(coreProductComponent, stockQuantityProvider);

        getAllService.execute(Mockito.any());

        Mockito.verify(coreProductComponent).getAll(Mockito.any());
        Mockito.verify(stockQuantityProvider).provide(Mockito.anyString(), Mockito.any());
    }
}
