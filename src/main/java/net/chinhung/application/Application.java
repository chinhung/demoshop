package net.chinhung.application;

import net.chinhung.application.component.endpoint.EndpointComponent;
import net.chinhung.application.endpoint.order.OrderEndpoint;
import net.chinhung.application.endpoint.product.ProductEndpoint;

public class Application {

    private final EndpointComponent endpointComponent;

    Application(EndpointComponent endpointComponent) {
        this.endpointComponent = endpointComponent;
    }

    public ProductEndpoint getProductEndpoint() {
        return endpointComponent.getProductEndpoint();
    }

    public OrderEndpoint getOrderEndpoint() {
        return endpointComponent.getOrderEndpoint();
    }
}
