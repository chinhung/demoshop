package net.chinhung.application.endpoint.product.impl.create_product;

import net.chinhung.application.endpoint.product.CreateProduct;
import net.chinhung.application.endpoint.product.impl.ProductConverter;
import net.chinhung.application.product.Product;
import net.chinhung.application.component.product.ProductComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static util.TestUtil.ANY_INT;
import static util.TestUtil.ANY_STRING;

public class CreateProductServiceTest {

    @Mock private ProductComponent productComponent;
    @Mock private ProductConverter productConverter;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        final CreateProductService createProductService = new CreateProductService(productComponent, productConverter);

        final CreateProduct anyCommand = new CreateProduct();
        anyCommand.setName(ANY_STRING);
        anyCommand.setPrice(ANY_INT.toString());
        createProductService.execute(anyCommand);

        Mockito.verify(productComponent).createProduct(ANY_STRING, ANY_INT);
        Mockito.verify(productConverter).toProductDTO((Product) Mockito.any());
    }
}
