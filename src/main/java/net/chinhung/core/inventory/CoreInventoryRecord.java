package net.chinhung.core.inventory;

import java.util.Objects;

public class CoreInventoryRecord {

    private final String id;

    private final String name;

    private final String productId;

    private final Integer stockQuantity;

    public CoreInventoryRecord(String id, String name, String productId, Integer stockQuantity) {
        this.id = id;
        this.name = name;
        this.productId = productId;
        this.stockQuantity = stockQuantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProductId() {
        return productId;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoreInventoryRecord that = (CoreInventoryRecord) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
