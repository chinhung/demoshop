package net.chinhung.application.component.product.impl.create;

import net.chinhung.application.component.product.Create;
import net.chinhung.application.component.product.CreateResult;
import net.chinhung.application.product.Product;
import net.chinhung.application.product.StockQuantityProvider;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.core.component.product.CoreProductComponent;

public class CreateService implements CommandService<Create, CreateResult> {

    private final CoreProductComponent coreProductComponent;
    private final StockQuantityProvider stockQuantityProvider;

    CreateService(
            final CoreProductComponent coreProductComponent,
            final StockQuantityProvider stockQuantityProvider
    ) {
        this.coreProductComponent = coreProductComponent;
        this.stockQuantityProvider = stockQuantityProvider;
    }

    @Override
    public CreateResult execute(Create command) {
        final net.chinhung.core.component.product.Create create = new net.chinhung.core.component.product.Create(command.getName(), command.getPrice());
        final net.chinhung.core.component.product.CreateResult result = coreProductComponent.create(create);

        final String productId = result.getCreated().getId();
        return new CreateResult(new Product(result.getCreated(), stockQuantityProvider.provide(productId)));
    }

    public static CommandService<Create, CreateResult> getImplementer(CoreProductComponent coreProductComponent, StockQuantityProvider stockQuantityProvider) {
        return new CreateService(coreProductComponent, stockQuantityProvider);
    }
}
