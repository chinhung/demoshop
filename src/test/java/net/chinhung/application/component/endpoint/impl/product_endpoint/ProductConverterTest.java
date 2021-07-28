package net.chinhung.application.component.endpoint.impl.product_endpoint;

import net.chinhung.application.endpoint.product.ProductDTO;
import net.chinhung.application.endpoint.product.impl.ProductConverter;
import net.chinhung.application.product.Product;
import net.chinhung.core.product.CoreProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductConverterTest {

    @Test
    public void testConvert() {
        final ProductConverter converter = new ProductConverter();
        final Product product = new Product(new CoreProduct("someId", "someName", 1000), () -> 1);

        final ProductDTO dto = converter.toProductDTO(product);

        Assertions.assertEquals(product.getId(), dto.getId());
        Assertions.assertEquals(product.getName(), dto.getName());
        Assertions.assertEquals(product.getPrice(), dto.getPrice());
        Assertions.assertEquals(product.getStockQuantity().getValue(), dto.getStockQuantity());
    }
}
