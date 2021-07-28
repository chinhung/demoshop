package net.chinhung.application.component.product.impl.create;

import net.chinhung.application.component.product.Create;
import net.chinhung.application.component.product.CreateResult;
import net.chinhung.application.product.StockQuantityProvider;
import net.chinhung.core.product.CoreProduct;
import net.chinhung.core.component.product.CoreProductComponent;
import net.chinhung.fundamental.aspect.CommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static util.TestUtil.ANY_INT;
import static util.TestUtil.ANY_STRING;

public class CreateServiceTest {

    @Mock private CoreProductComponent coreProductComponent;
    @Mock private StockQuantityProvider stockQuantityProvider;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        final net.chinhung.core.component.product.CreateResult anyResult = new net.chinhung.core.component.product.CreateResult(new CoreProduct(ANY_STRING, ANY_STRING, ANY_INT));
        Mockito.when(coreProductComponent.create(Mockito.any())).thenReturn(anyResult);
        final CommandService<Create, CreateResult> createService = new CreateService(coreProductComponent, stockQuantityProvider);

        final Create anyCommand = new Create(ANY_STRING, ANY_INT);
        createService.execute(anyCommand);

        Mockito.verify(coreProductComponent).create(Mockito.any());
        Mockito.verify(stockQuantityProvider).provide(Mockito.anyString());
    }
}
