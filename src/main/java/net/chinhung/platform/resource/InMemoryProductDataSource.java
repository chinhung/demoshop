package net.chinhung.platform.resource;

import net.chinhung.core.product.CoreProduct;
import net.chinhung.core.component.product.impl.CoreProductDataSource;
import net.chinhung.core.component.product.Create;
import net.chinhung.core.component.product.CreateResult;
import net.chinhung.core.component.product.Delete;
import net.chinhung.core.component.product.DeleteResult;
import net.chinhung.core.component.product.GetAll;
import net.chinhung.core.component.product.GetAllResult;
import net.chinhung.core.component.product.Update;
import net.chinhung.core.component.product.UpdateResult;

import java.util.ArrayList;
import java.util.List;

public class InMemoryProductDataSource implements CoreProductDataSource {

    private final List<CoreProduct> coreProducts = new ArrayList<>();

    public InMemoryProductDataSource() {
        CoreProduct iPhone = new CoreProduct("1", "iPhone", 30000);
        CoreProduct macbook = new CoreProduct("2", "macbook", 70000);
        CoreProduct iMac = new CoreProduct("3", "iMac", 50000);
        coreProducts.add(iPhone);
        coreProducts.add(macbook);
        coreProducts.add(iMac);
    }

    public GetAllResult getAll(GetAll command) {
        return new GetAllResult(new ArrayList<>(coreProducts));
    }

    @Override
    public UpdateResult update(Update command) {
        throw new RuntimeException("update product not support");
    }

    @Override
    public CreateResult create(Create command) {
        String id = String.valueOf(coreProducts.size() + 1);
        CoreProduct coreProduct = new CoreProduct(id, command.getName(), command.getPrice());
        coreProducts.add(coreProduct);
        return new CreateResult(coreProduct);
    }

    @Override
    public DeleteResult delete(Delete command) {
        throw new RuntimeException("delete product not support");
    }
}
