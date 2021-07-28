package net.chinhung.application.component.inventory;

import java.util.ArrayList;
import java.util.List;

public class InventoryQueryResult {

    private final List<InventoryQuery> inStock = new ArrayList<>();
    private final List<InventoryQuery> outOfStock = new ArrayList<>();

    public void inStock(final InventoryQuery query) {
        inStock.add(query);
    }

    public void outOfStock(final InventoryQuery query) {
        outOfStock.add(query);
    }

    public List<InventoryQuery> getInStockQueries() {
        return new ArrayList<>(inStock);
    }

    public List<InventoryQuery> getOutOfStockQueries() {
        return new ArrayList<>(outOfStock);
    }
}
