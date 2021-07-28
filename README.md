# DemoShop

## Overview
A simple online shop application which includes 3 major functionality: product, order and inventory. The stock quantity of a particular product will be decreased when an order contains that product is approved. Otherwise, the order will not be able to be approved while the product is out of stock.

## Highlights
- code coverage is 80% above
- protect the domain logic by acceptance test, refactor the codebase confidently
- implement `Aspect-Oriented Programming(AOP)` with Decorator Pattern
- modular design, no circle dependency exists amount the modules
- encapsulate the dependencies on the 3rd-party libraries and framework

## Run tests
Run test cases by `gradle` or `gradle wrapper`:

### Unit Test
```
./gradlew test
```
Get the code coverage report at `./build/jacocoHtml/index.html` when the unit test run is finished.

### Acceptance Test
```
./gradlew cucumber
```

When the acceptance test run is finished, use the following command to generate the cucumber report at `./build/reports/cucumber-reports/prettyCucumberReport`:
```
./gradlew generateCucumberReports
```

## Design Highlights

### Modular Design
There are 7 modules: `CoreProduct`, `CoreOrder`, `CoreInventory`, `Product`, `Order`, `Inventory` and `Endpoint`. There is no circle dependency amount the modules, the dependency diagram is below:

![元件圖](https://i.imgur.com/Mh7IB5b.png)

The data layer contains 3 modules: `CoreProduct`, `CoreOrder` and `CoreInventory`; the logic layer contains 3 modules: `Product`, `Order` and `Inventory`; the presentation layer contains one module: `Endpoint`.

### StockQuantity
`Product`object contains `stockQuantity` field, however, the stock quantity data is stored in the `Inventory` module. It means that we have to query the stock quantity of particular product when we create any `Product` object if the type of `stockQuantity` field being `Integer`. Since not all scenarios require the stock quantity value, it would be a waste to query the data every times. An appropriate design is to abstract the stock quantity as an interface: `StockQuantity`, which is implemented by class `LazyStockQuantity` allowing us to query the stock quantity data only when we really need them. Also, a single interface `StockQuantity` helps the team to form a ubiquitous language, which is a fundamental concept in `Domain-Driven Design`.

## Todo
- refactoring
    - [ ] wrapper layer of the acceptance test which brings more intentions of the test cases to the reader
    
- develop other functions