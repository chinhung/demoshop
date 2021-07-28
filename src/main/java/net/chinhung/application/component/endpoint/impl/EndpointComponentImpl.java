package net.chinhung.application.component.endpoint.impl;

import io.github.chinhung.pointwave.PointWave;
import net.chinhung.application.component.endpoint.EndpointComponent;
import net.chinhung.application.endpoint.order.OrderEndpoint;
import net.chinhung.application.endpoint.product.ProductEndpoint;
import net.chinhung.application.endpoint.order.impl.OrderConverter;
import net.chinhung.application.endpoint.order.OrderDTO;
import net.chinhung.application.endpoint.order.impl.OrderEndpointImpl;
import net.chinhung.application.endpoint.order.CreateOrder;
import net.chinhung.application.endpoint.order.impl.create_order.CreateOrderService;
import net.chinhung.application.endpoint.order.impl.find_by_id.FindById;
import net.chinhung.application.endpoint.order.impl.find_by_id.FindByIdService;
import net.chinhung.application.endpoint.order.impl.find_orders.FindOrders;
import net.chinhung.application.endpoint.order.impl.find_orders.FindOrdersService;
import net.chinhung.application.endpoint.product.impl.ProductConverter;
import net.chinhung.application.endpoint.product.ProductDTO;
import net.chinhung.application.endpoint.product.impl.ProductEndpointImpl;
import net.chinhung.application.endpoint.product.CreateProduct;
import net.chinhung.application.endpoint.product.impl.create_product.CreateProductService;
import net.chinhung.application.endpoint.product.impl.find_products.FindProducts;
import net.chinhung.application.endpoint.product.impl.find_products.FindProductsService;
import net.chinhung.application.component.order.OrderComponent;
import net.chinhung.application.component.product.ProductComponent;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;
import net.chinhung.fundamental.aspect.validator.DefaultValidator;
import net.chinhung.fundamental.aspect.validator.ValidateActionDecorator;

import java.util.List;

public class EndpointComponentImpl implements EndpointComponent {

    private final ProductEndpoint productEndpoint;
    private final OrderEndpoint orderEndpoint;

    EndpointComponentImpl(
            final ProductEndpoint productEndpoint,
            final OrderEndpoint orderEndpoint
    ) {
        this.productEndpoint = productEndpoint;
        this.orderEndpoint = orderEndpoint;
    }

    @Override
    public ProductEndpoint getProductEndpoint() {
        return productEndpoint;
    }

    @Override
    public OrderEndpoint getOrderEndpoint() {
        return orderEndpoint;
    }

    public static EndpointComponent factory(
            final ProductComponent productComponent,
            final OrderComponent orderComponent
    ) {
        final ProductConverter productConverter = new ProductConverter();
        final QueryService<FindProducts, List<ProductDTO>> findProducts = new FindProductsService(productComponent, productConverter);
        final CommandService<CreateProduct, ProductDTO> createProduct =
                PointWave.decoratee((CommandService<CreateProduct, ProductDTO>) new CreateProductService(productComponent, productConverter))
                .decorated(c -> new ValidateActionDecorator<>(c, new DefaultValidator<>()))
                .complete();

        final OrderConverter orderConverter = new OrderConverter();
        final QueryService<FindOrders, List<OrderDTO>> findOrders = new FindOrdersService(orderComponent, orderConverter);
        final QueryService<FindById, OrderDTO> findById = new FindByIdService(orderComponent, orderConverter);
        final CommandService<CreateOrder, OrderDTO> createOrder = new CreateOrderService(orderComponent, orderConverter);

        final ProductEndpoint productEndpoint = new ProductEndpointImpl(findProducts, createProduct);
        final OrderEndpoint orderEndpoint = new OrderEndpointImpl(findOrders, findById, createOrder);

        return new EndpointComponentImpl(productEndpoint, orderEndpoint);
    }
}
