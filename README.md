# DemoShop

## Overview
A simple online shop application which includes 3 major functionality: product, order and inventory. The stock quantity of a particular product will be decreased when an order contains that product is approved. Otherwise, the order will not be able to be approved while the product is out of stock.

## Highlights
- clean codebase which is SOLID, easy to read, easy to test, and easy to maintain
- code coverage is 80% above
- protect the domain logic by acceptance test, refactor the codebase confidently
- implement `Aspect-Oriented Programming(AOP)` with Decorator Pattern
- modular design, no circle dependency exists amount the modules
- encapsulate the dependencies on the 3rd-party libraries and framework
- above 80% of code are less than 40 lines, and no one is more than 80 lines

## Run tests
Run test cases by `gradle` or `gradle wrapper`:

### Unit Test
```
./gradlew test
```
Get the code coverage report at `./build/jacocoHtml/index.html` when the unit test run is finished.

![code coverage report](https://i.imgur.com/jLdu9Ce.png)

### Acceptance Test
```
./gradlew cucumber
```

When the acceptance test run is finished, use the following command to generate the cucumber report at `./build/reports/cucumber-reports/prettyCucumberReport`:
```
./gradlew generateCucumberReports
```
![cucumber report](https://i.imgur.com/A9gZ4r7.png)

Here are acceptance test cases for example:

```Gherkin
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
```
All acceptance test cases are in the `./src/test/resources/features` folder.

## Design Concepts

### Modular Design
There are 7 modules: `CoreProduct`, `CoreOrder`, `CoreInventory`, `Product`, `Order`, `Inventory` and `Endpoint`. There is no circle dependency amount the modules, the dependency diagram is below:

![元件圖](https://i.imgur.com/Mh7IB5b.png)

The data layer contains 3 modules: `CoreProduct`, `CoreOrder` and `CoreInventory`; the logic layer contains 3 modules: `Product`, `Order` and `Inventory`; the presentation layer contains one module: `Endpoint`.

### Aspect-Oriented Programming

Aspect-Oriented Programming is implemented with Decorator Pattern. For example, one could simply decorate any `ActionService` with `decoratorWithActionValidator` to add validation functionality to a particular action.

### Encapsulate the Dependencies on the 3rd-party Libraries and Framework

The dependency on the Spring framework is encapsulated in the `net.chinhung.platform` package. The dependency on `javax.validation.Validator` is encapsulated in the `DefaultValidator` class.

### Short Units of Code

Short units of code are easy to read, easy to test, and easy to maintain. The statistics data shows that there are above 80% of code lower than 40 lines, and no one is higher than 80 lines.

![Statistics of Code Lines](https://i.imgur.com/kezLvL7.png)

### Abstract StockQuantity

`Product`object contains `stockQuantity` field, however, the stock quantity data is stored in the `Inventory` module. It means that we have to query the stock quantity of particular product when we create any `Product` object if the type of `stockQuantity` field being `Integer`. 

Since not all scenarios require the stock quantity value, it would be a waste to query the data every times. An appropriate design is to abstract the stock quantity as an interface: `StockQuantity`, which is implemented by class `LazyStockQuantity` allowing us to query the stock quantity data only when we really need them. 

Also, a single interface `StockQuantity` helps the team to form a ubiquitous language, which is a fundamental concept in `Domain-Driven Design`.

## Todo
- refactoring
    - [ ] wrapper layer of the acceptance test which brings more intentions of the test cases to the reader
    
- develop other functions