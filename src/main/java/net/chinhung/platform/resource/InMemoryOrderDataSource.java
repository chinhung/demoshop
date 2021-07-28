package net.chinhung.platform.resource;

import net.chinhung.core.order.CoreItem;
import net.chinhung.core.order.CoreOrder;
import net.chinhung.core.order.CoreStatus;
import net.chinhung.core.component.order.impl.CoreOrderDataSource;
import net.chinhung.core.component.order.Create;
import net.chinhung.core.component.order.CreateResult;
import net.chinhung.core.component.order.Delete;
import net.chinhung.core.component.order.DeleteResult;
import net.chinhung.core.component.order.FindById;
import net.chinhung.core.component.order.FindByIdResult;
import net.chinhung.core.component.order.GetAll;
import net.chinhung.core.component.order.GetAllResult;
import net.chinhung.core.component.order.Update;
import net.chinhung.core.component.order.UpdateResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class InMemoryOrderDataSource implements CoreOrderDataSource {

    private final List<CoreOrder> orders = new ArrayList<>();

    public InMemoryOrderDataSource() {
        CoreItem item = new CoreItem("iPhone", "1", 1);
        CoreOrder order = new CoreOrder("1", Collections.singletonList(item), CoreStatus.APPROVED);
        orders.add(order);
    }

    @Override
    public CreateResult create(Create command) {
        String id = String.valueOf(orders.size() + 1);
        CoreOrder order = new CoreOrder(id, command.getItems(), CoreStatus.APPROVED);
        orders.add(order);
        return new CreateResult(order);
    }

    @Override
    public DeleteResult delete(Delete command) {
        throw new RuntimeException();
    }

    @Override
    public FindByIdResult findById(FindById command) {
        Optional<CoreOrder> founded = orders.stream().filter(order -> order.getId().equals(command.getId())).findFirst();
        return new FindByIdResult(founded);
    }

    @Override
    public GetAllResult getAll(GetAll command) {
        return new GetAllResult(new ArrayList<>(orders));
    }

    @Override
    public UpdateResult update(Update command) {
        throw new RuntimeException();
    }
}
