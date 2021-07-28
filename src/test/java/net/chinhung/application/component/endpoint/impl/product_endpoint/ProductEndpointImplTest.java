package net.chinhung.application.component.endpoint.impl.product_endpoint;

import net.chinhung.application.endpoint.product.impl.ProductEndpointImpl;
import net.chinhung.application.endpoint.product.CreateProduct;
import net.chinhung.application.endpoint.product.impl.find_products.FindProducts;
import net.chinhung.application.endpoint.product.ProductDTO;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class ProductEndpointImplTest {

    @Mock private QueryService<FindProducts, List<ProductDTO>> findProducts;
    @Mock private CommandService<CreateProduct, ProductDTO> createProduct;

    private ProductEndpointImpl instance = new ProductEndpointImpl(findProducts, createProduct);

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        instance = new ProductEndpointImpl(findProducts, createProduct);
    }

    @Test
    public void findProducts() {
        instance.findProducts();

        Mockito.verify(findProducts).execute(Mockito.any());
    }

    @Test
    public void createProduct() {
        instance.createProduct(Mockito.any());

        Mockito.verify(createProduct).execute(Mockito.any());
    }
}
