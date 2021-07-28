Feature: Find orders

    Scenario: Find all orders
        When query for all orders
        Then got an order list

    Scenario: Find order by id, order exists
        When query order by id: 1
        Then got an order

    Scenario: Find order by id, order not exists
        When query order by id: noSuchOrderId
        Then got error message "order not found"