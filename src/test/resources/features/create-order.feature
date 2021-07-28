Feature: Create order

    Scenario: Product is in stock
        Given the stock quantities of the following products are excepted:
            | id | name    | price | stockQuantity |
            | 2  | macbook | 70000 | 2             |
        * order contains the following order lines not exists:
            | productId | name    | quantity |
            | 2         | macbook | 2        |
        When create order with the following order lines:
            | productId | name    | quantity |
            | 2         | macbook | 2        |
        Then the stock quantities of the following products are excepted:
            | id | name    | price | stockQuantity |
            | 2  | macbook | 70000 | 0             |
        * order contains the following order lines exists:
            | productId | name    | quantity |
            | 2         | macbook | 2        |

    Scenario: Product is out of stock
        Given the stock quantities of the following products are excepted:
            | id | name    | price | stockQuantity |
            | 1  | iPhone  | 30000 | 1             |
            | 2  | macbook | 70000 | 2             |
            | 3  | iMac    | 50000 | 0             |
        When create order with the following order lines:
            | productId | name    | quantity |
            | 1         | iPhone  | 1        |
            | 2         | macbook | 3        |
            | 3         | iMac    | 1        |
        Then got error message "products out of stock: macbook, iMac"