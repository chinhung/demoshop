package net.chinhung.core.component.order;

import net.chinhung.core.order.CoreItem;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

public class Create {

    @Size(min=1)
    private final List<@Valid CoreItem> coreItems;

    public Create(final List<CoreItem> coreItems) {
        this.coreItems = coreItems;
    }

    public List<CoreItem> getItems() {
        return coreItems;
    }
}
