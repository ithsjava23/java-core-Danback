package org.example;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductRecord {
    private final UUID id;
    private final String name;
    private final Category category;
    private BigDecimal price;

    public ProductRecord(UUID id, String name, Category category, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductRecord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                '}';
    }

    public UUID uuid() {
        return id;
    }

    public Category category() {
        return category;
    }

    public BigDecimal price() {
        return price;
    }

}
