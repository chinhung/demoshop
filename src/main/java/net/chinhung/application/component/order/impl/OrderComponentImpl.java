package net.chinhung.application.component.order.impl;

import net.chinhung.application.component.order.Create;
import net.chinhung.application.component.order.CreateResult;
import net.chinhung.application.component.order.OrderComponent;
import net.chinhung.application.component.order.impl.create.CreateService;
import net.chinhung.application.component.order.impl.create.InventoryQueryDecorator;
import net.chinhung.application.component.order.impl.create.InventoryUpdateDecorator;
import net.chinhung.application.component.order.impl.find_by_id.FindByIdService;
import net.chinhung.application.component.order.impl.get_all.GetAllService;
import net.chinhung.application.component.inventory.InventoryComponent;
import net.chinhung.application.order.Order;
import net.chinhung.application.component.order.FindByIdResult;
import net.chinhung.application.component.order.GetAll;
import net.chinhung.application.component.order.GetAllResult;
import net.chinhung.application.component.order.FindById;
import net.chinhung.core.component.order.CoreOrderComponent;
import net.chinhung.fundamental.aspect.CommandService;
import net.chinhung.fundamental.aspect.QueryService;
import net.chinhung.fundamental.aspect.validator.DecorateWithDefaultActionValidator;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class OrderComponentImpl implements OrderComponent {

    private final QueryService<GetAll, GetAllResult> getAllService;
    private final QueryService<FindById, FindByIdResult> findByIdService;
    private final CommandService<Create, CreateResult> createService;

    OrderComponentImpl(
            final QueryService<GetAll, GetAllResult> getAllService,
            final QueryService<FindById, FindByIdResult> findByIdService,
            final CommandService<Create, CreateResult> createService
    ) {
        this.getAllService = getAllService;
        this.findByIdService = findByIdService;
        this.createService = createService;
    }

    @Override
    public Stream<Order> findOrders(GetAll query) {
        return getAllService.execute(query).getFounded();
    }

    @Override
    public Optional<Order> findById(FindById query) {
        return findByIdService.execute(query).getFounded();
    }

    @Override
    public Order create(Create command) {
        return createService.execute(command).getCreated();
    }

    public static OrderComponent factory(OrderResource resource) {
        final CoreOrderComponent coreOrderComponent = resource.coreOrderComponent();
        final InventoryComponent inventoryComponent = resource.inventoryComponent();

        final QueryService<GetAll, GetAllResult> getAllServiceImpl = GetAllService.getImplementer(coreOrderComponent);
        final QueryService<FindById, FindByIdResult> findByIdServiceImpl = FindByIdService.getImplementer(coreOrderComponent);
        final CommandService<Create, CreateResult> createServiceImpl = CreateService.getImplementer(coreOrderComponent);

        final Function<CommandService<Create, CreateResult>, CommandService<Create, CreateResult>> decorateWithInventoryQuery = c -> new InventoryQueryDecorator(c, inventoryComponent);
        final Function<CommandService<Create, CreateResult>, CommandService<Create, CreateResult>> decorateWithInventoryUpdate = c -> new InventoryUpdateDecorator(c, inventoryComponent);

        final QueryService<GetAll, GetAllResult> getAllService = OrderServiceFactory.getAllService(getAllServiceImpl);
        final QueryService<FindById, FindByIdResult> findByIdService = OrderServiceFactory.findByIdService(findByIdServiceImpl, new DecorateWithDefaultActionValidator<>());
        final CommandService<Create, CreateResult> createService = OrderServiceFactory.createService(createServiceImpl, decorateWithInventoryQuery, decorateWithInventoryUpdate, new DecorateWithDefaultActionValidator<>());

        return new OrderComponentImpl(getAllService, findByIdService, createService);
    }
}
