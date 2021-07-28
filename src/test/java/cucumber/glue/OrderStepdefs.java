package cucumber.glue;

import net.chinhung.application.endpoint.order.OrderDTO;
import net.chinhung.application.endpoint.order.OrderEndpoint;
import net.chinhung.application.endpoint.order.OrderLineDTO;
import net.chinhung.application.endpoint.order.CreateOrder;
import net.chinhung.application.endpoint.order.OrderLine;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@ScenarioScoped
public class OrderStepdefs {

    private final OrderEndpoint orderEndpoint;

    private List<OrderDTO> orders;
    private OrderDTO order;
    private String errorMessage;

    @DataTableType
    public OrderLineDTO orderLineEntry(final Map<String, String> entry) {
        final OrderLineDTO line = new OrderLineDTO();
        line.setName(entry.get("name"));
        line.setProductId(entry.get("productId"));
        line.setQuantity(Integer.valueOf(entry.get("quantity")));
        return line;
    }

    @Inject
    public OrderStepdefs(final SharedStates sharedStates) {
        this.orderEndpoint = sharedStates.getApplication().getOrderEndpoint();
    }

    @Given("order contains the following order lines not exists:")
    public void orderContainsTheFollowingOrderLinesNotExists(final List<OrderLineDTO> orderLines) {
        queryForAllOrders();
        Optional<OrderDTO> filtered = this.orders.stream().filter(order -> OrderUtil.containsOrderLines(order, orderLines)).findAny();
        Assertions.assertFalse(filtered.isPresent(), "order contains the following order lines exists: " + orderLines);
    }

    @Given("order contains the following order lines exists:")
    public void orderContainsTheFollowingOrderLinesExists(final List<OrderLineDTO> orderLines) {
        queryForAllOrders();
        Optional<OrderDTO> filtered = this.orders.stream().filter(order -> OrderUtil.containsOrderLines(order, orderLines)).findAny();
        Assertions.assertTrue(filtered.isPresent(), "order contains the following order lines not exists: " + orderLines);
    }

    @When("query for all orders")
    public void queryForAllOrders() {
        orders = orderEndpoint.findOrders();
    }

    @When("^query order by id: (.*)$")
    public void queryOrderById(String id) {
        try {
            order = orderEndpoint.findById(id);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("got an order list")
    public void gotAnOrderList() {
        Assertions.assertNotNull(orders, "order list not exists");
        Assertions.assertTrue(orders.size() > 0, "no order in the order list");
    }

    @When("create order with the following order lines:")
    public void createOrderWithTheFollowingOrderLines(final List<OrderLineDTO> orderLines) {
        final CreateOrder createOrder = new CreateOrder();
        createOrder.setOrderLines(orderLines.stream().map(orderLine -> {
            final OrderLine line = new OrderLine();
            line.setName(orderLine.getName());
            line.setProductId(orderLine.getProductId());
            line.setQuantity(orderLine.getQuantity());
            return line;
        }).collect(Collectors.toList()));

        try {
            orderEndpoint.createOrder(createOrder);
        } catch (Exception e) {
            errorMessage =  e.getMessage();
        }
    }

    @Then("got error message {string}")
    public void gotErrorMessage(String excepted) {
        Assertions.assertEquals(excepted, this.errorMessage);
    }

    @Then("got an order")
    public void gotAnOrder() {
        Assertions.assertNotNull(order, "order not exists");
    }
}

class OrderUtil {

    static boolean containsOrderLines(final OrderDTO order, final List<OrderLineDTO> target) {
        if (order.getOrderLines().size() != target.size()) {
            return false;
        }
        final List<OrderLineDTO> filtered = target.stream().filter(orderLine -> containsOrderLine(order.getOrderLines(), orderLine)).collect(Collectors.toList());
        return filtered.size() == target.size();
    }

    private static boolean containsOrderLine(final List<OrderLineDTO> orderLines, final OrderLineDTO target) {
        final Optional<OrderLineDTO> filtered = orderLines.stream().filter(orderLine -> {
            final boolean namePass = orderLine.getName().equals(target.getName());
            final boolean productIdPass = orderLine.getProductId().equals(target.getProductId());
            final boolean quantityPass = orderLine.getQuantity().equals(target.getQuantity());
            return namePass && productIdPass && quantityPass;
        }).findAny();
        return filtered.isPresent();
    }
}
