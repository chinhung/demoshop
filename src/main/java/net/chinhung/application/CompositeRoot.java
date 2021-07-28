package net.chinhung.application;

import net.chinhung.application.component.endpoint.impl.EndpointComponentImpl;
import net.chinhung.application.component.order.impl.OrderComponentImpl;
import net.chinhung.application.component.inventory.InventoryComponent;
import net.chinhung.application.component.inventory.impl.InventoryComponentImpl;
import net.chinhung.application.component.inventory.impl.InventoryResource;
import net.chinhung.application.component.order.OrderComponent;
import net.chinhung.application.component.order.impl.OrderResource;
import net.chinhung.application.component.product.ProductComponent;
import net.chinhung.application.component.product.impl.ProductComponentImpl;
import net.chinhung.application.component.product.impl.ProductResource;
import net.chinhung.core.component.inventory.CoreInventoryComponent;
import net.chinhung.core.component.inventory.impl.CoreInventoryComponentImpl;
import net.chinhung.core.component.inventory.impl.CoreInventoryResource;
import net.chinhung.core.component.order.CoreOrderComponent;
import net.chinhung.core.component.order.impl.CoreOrderComponentImpl;
import net.chinhung.core.component.order.impl.CoreOrderResource;
import net.chinhung.core.component.product.CoreProductComponent;
import net.chinhung.application.component.endpoint.EndpointComponent;
import net.chinhung.core.component.product.impl.CoreProductComponentImpl;
import net.chinhung.core.component.product.impl.CoreProductResource;

public class CompositeRoot {

    public static Application composite(Resource resource) {
        CoreProductResource coreProductResource = new CoreProductResource(resource.coreProductDataSource());
        CoreProductComponent coreProductComponent = CoreProductComponentImpl.factory(coreProductResource);

        CoreInventoryResource coreInventoryResource = new CoreInventoryResource(resource.coreInventoryDataSource());
        CoreInventoryComponent coreInventoryComponent = CoreInventoryComponentImpl.factory(coreInventoryResource);

        InventoryResource inventoryResource = new InventoryResource(coreInventoryComponent);
        InventoryComponent inventoryComponent = InventoryComponentImpl.factory(inventoryResource);

        ProductResource productResource = new ProductResource(coreProductComponent, inventoryComponent);
        ProductComponent productComponent = ProductComponentImpl.factory(productResource);

        CoreOrderResource coreOrderResource = new CoreOrderResource(resource.coreOrderDataSource());
        CoreOrderComponent coreOrderComponent = CoreOrderComponentImpl.factory(coreOrderResource);

        OrderResource orderResource = new OrderResource(coreOrderComponent, inventoryComponent);
        OrderComponent orderComponent = OrderComponentImpl.factory(orderResource);

        EndpointComponent endpointComponent = EndpointComponentImpl.factory(productComponent, orderComponent);

        return new Application(endpointComponent);
    }
}
