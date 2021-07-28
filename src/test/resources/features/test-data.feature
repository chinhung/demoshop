Feature: Test data

    Scenario: Test products
        Given the following products exist:
            | id | name    | price | stockQuantity |
            | 1  | iPhone  | 30000 | 1             |
            | 2  | macbook | 70000 | 2             |
            | 3  | iMac    | 50000 | 0             |

    Scenario: Test orders
        Given order contains the following order lines exists:
            | productId | name   | quantity |
            | 1         | iPhone | 1        |