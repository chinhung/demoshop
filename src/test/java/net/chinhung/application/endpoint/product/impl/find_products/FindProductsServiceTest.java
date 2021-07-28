package net.chinhung.application.endpoint.product.impl.find_products;

import net.chinhung.application.endpoint.product.impl.ProductConverter;
import net.chinhung.application.product.Product;
import net.chinhung.application.component.product.ProductComponent;
import net.chinhung.core.product.CoreProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.stream.Stream;

import static util.TestUtil.ANY_STRING;
import static util.TestUtil.ANY_INT;

public class FindProductsServiceTest {

    @Mock private ProductComponent productComponent;
    @Mock private ProductConverter productConverter;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        final FindProductsService findProductsService = new FindProductsService(productComponent, productConverter);
        final Stream<Product> anyResult = Stream.of(new Product(new CoreProduct(ANY_STRING, ANY_STRING, ANY_INT), () -> 1));
        Mockito.when(productComponent.findProducts()).thenReturn(anyResult);

        final FindProducts anyQuery = new FindProducts();
        findProductsService.execute(anyQuery);

        Mockito.verify(productComponent).findProducts();
        Mockito.verify(productConverter).toProductDTO(anyResult);
    }
}
