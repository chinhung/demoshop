Feature: Create product

    Scenario: Create product
        Given product iPod not exists
        When create product iPod with price 7000
        Then the following products exist:
            | id  | name | price | stockQuantity |
            | any | iPod | 7000  | 0             |
