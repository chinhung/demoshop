package net.chinhung.application.order;

import net.chinhung.core.order.CoreStatus;

public enum Status {
    APPROVED,
    SHIPPED;

    static Status from(CoreStatus coreStatus) {
        switch(coreStatus) {
            case APPROVED:
                return APPROVED;
            case SHIPPED:
                return SHIPPED;
            default:
                throw new RuntimeException("unknown CoreStatus");
        }
    }
}
