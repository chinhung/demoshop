package net.chinhung.application.component.product.impl.get_all;

import net.chinhung.application.component.product.GetAll;
import net.chinhung.application.component.product.GetAllResult;
import net.chinhung.application.product.Product;
import net.chinhung.application.product.StockQuantityProvider;
import net.chinhung.core.product.CoreProduct;
import net.chinhung.fundamental.aspect.QueryService;
import net.chinhung.core.component.product.CoreProductComponent;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllService implements QueryService<GetAll, GetAllResult> {

    private final CoreProductComponent coreProductComponent;
    private final StockQuantityProvider stockQuantityProvider;

    GetAllService(
            final CoreProductComponent coreProductComponent,
            final StockQuantityProvider stockQuantityProvider
    ) {
        this.coreProductComponent = coreProductComponent;
        this.stockQuantityProvider = stockQuantityProvider;
    }

    @Override
    public GetAllResult execute(final GetAll command) {
        final net.chinhung.core.component.product.GetAllResult result = coreProductComponent.getAll(new net.chinhung.core.component.product.GetAll());

        final List<String> productIds = result.getFounded().map(CoreProduct::getId).collect(Collectors.toList());
        final List<Product> founded = result.getFounded().map(p -> new Product(p, stockQuantityProvider.provide(p.getId(), productIds))).collect(Collectors.toList());

        return new GetAllResult(founded);
    }

    public static QueryService<GetAll, GetAllResult> getImplementer(CoreProductComponent coreProductComponent, StockQuantityProvider stockQuantityProvider) {
        return new GetAllService(coreProductComponent, stockQuantityProvider);
    }
}
