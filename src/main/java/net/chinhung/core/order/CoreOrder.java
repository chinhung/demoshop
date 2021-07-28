package net.chinhung.core.order;

import java.util.List;

public class CoreOrder {

    private final String id;

    private final List<CoreItem> coreItems;

    private final CoreStatus coreStatus;

    public CoreOrder(final String id, final List<CoreItem> coreItems, final CoreStatus coreStatus) {
        this.id = id;
        this.coreItems = coreItems;
        this.coreStatus = coreStatus;
    }

    public String getId() {
        return id;
    }

    public List<CoreItem> getItems() {
        return coreItems;
    }

    public CoreStatus getStatus() {
        return coreStatus;
    }
}
