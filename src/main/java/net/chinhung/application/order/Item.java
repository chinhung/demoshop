package net.chinhung.application.order;

import net.chinhung.core.order.CoreItem;

import javax.validation.Valid;

public class Item {

    @Valid
    private final CoreItem coreItem;

    public Item(final CoreItem coreItem) {
        this.coreItem = coreItem;
    }

    public String getName() {
        return coreItem.getName();
    }

    public String getProductId() {
        return coreItem.getProductId();
    }

    public Integer getQuantity() {
        return coreItem.getQuantity();
    }
}
