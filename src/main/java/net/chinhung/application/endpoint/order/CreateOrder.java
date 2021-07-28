package net.chinhung.application.endpoint.order;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

public class CreateOrder {

    @Size(min = 1)
    private List<@Valid OrderLine> orderLines;

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(final List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
}
