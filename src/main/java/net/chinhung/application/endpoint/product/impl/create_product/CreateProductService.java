package net.chinhung.application.endpoint.product.impl.create_product;

import net.chinhung.application.endpoint.product.CreateProduct;
import net.chinhung.application.endpoint.product.impl.ProductConverter;
import net.chinhung.application.endpoint.product.ProductDTO;
import net.chinhung.application.product.Product;
import net.chinhung.application.component.product.ProductComponent;
import net.chinhung.fundamental.aspect.CommandService;

public class CreateProductService implements CommandService<CreateProduct, ProductDTO> {

    private final ProductComponent productComponent;
    private final ProductConverter productConverter;

    public CreateProductService(final ProductComponent productComponent, final ProductConverter productConverter) {
        this.productComponent = productComponent;
        this.productConverter = productConverter;
    }

    @Override
    public ProductDTO execute(CreateProduct command) {
        Product created = productComponent.createProduct(command.getName(), new Integer(command.getPrice()));
        return productConverter.toProductDTO(created);
    }
}
