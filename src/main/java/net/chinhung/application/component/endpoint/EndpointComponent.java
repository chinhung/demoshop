package net.chinhung.application.component.endpoint;

import net.chinhung.application.endpoint.order.OrderEndpoint;
import net.chinhung.application.endpoint.product.ProductEndpoint;

public interface EndpointComponent {

    ProductEndpoint getProductEndpoint();

    OrderEndpoint getOrderEndpoint();
}
