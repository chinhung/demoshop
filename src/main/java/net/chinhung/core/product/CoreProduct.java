package net.chinhung.core.product;

public class CoreProduct {

    private final String id;

    private final String name;

    private final Integer price;

    public CoreProduct(final String id, final String name, final Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}
