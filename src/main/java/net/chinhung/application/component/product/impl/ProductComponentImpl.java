package net.chinhung.application.component.product.impl;

import net.chinhung.application.component.product.ProductComponent;
import net.chinhung.application.component.inventory.InventoryComponent;
import net.chinhung.application.product.Product;
import net.chinhung.application.component.product.Create;
import net.chinhung.application.component.product.CreateResult;
import net.chinhung.application.component.product.GetAll;
import net.chinhung.application.component.product.GetAllResult;
import net.chinhung.application.component.product.impl.create.CreateService;
import net.chinhung.application.component.product.impl.get_all.GetAllService;
import net.chinhung.application.product.StockQuantityProvider;
import net.chinhung.application.product.lazy_stock_quantity.LazyStockQuantityProvider;
import net.chinhung.application.product.lazy_stock_quantity.StockQuantityStoreProvider;
import net.chinhung.core.component.product.CoreProductComponent;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;
import net.chinhung.fundamental.aspect.validator.DecorateWithDefaultActionValidator;

import java.util.stream.Stream;

public class ProductComponentImpl implements ProductComponent {

    private final QueryService<GetAll, GetAllResult> getAllService;
    private final CommandService<Create, CreateResult> createService;

    ProductComponentImpl(
            final QueryService<GetAll, GetAllResult> getAllService,
            final CommandService<Create, CreateResult> createService
    ) {
        this.getAllService = getAllService;
        this.createService = createService;
    }

    @Override
    public Stream<Product> findProducts() {
        return getAllService.execute(new GetAll()).getFounded();
    }

    @Override
    public Product createProduct(final String name, final Integer price) {
        return createService.execute(new Create(name, price)).getCreated();
    }


    public static ProductComponent factory(final ProductResource productResource) {
        final CoreProductComponent coreProductComponent = productResource.coreProductComponent();
        final InventoryComponent inventoryComponent = productResource.inventoryComponent();

        final StockQuantityStoreProvider stockQuantityStoreProvider = new StockQuantityStoreProvider(inventoryComponent);
        final StockQuantityProvider stockQuantityProvider = new LazyStockQuantityProvider(stockQuantityStoreProvider);

        final QueryService<GetAll, GetAllResult> getAllServiceImpl = GetAllService.getImplementer(coreProductComponent, stockQuantityProvider);
        final CommandService<Create, CreateResult> createServiceImpl = CreateService.getImplementer(coreProductComponent, stockQuantityProvider);

        final QueryService<GetAll, GetAllResult> getAllService = ProductServiceFactory.getAllService(getAllServiceImpl);
        final CommandService<Create, CreateResult> createService = ProductServiceFactory.createService(createServiceImpl, new DecorateWithDefaultActionValidator<>());

        return new ProductComponentImpl(getAllService, createService);
    }
}
