package org.example;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductRecord {
    private final UUID id;
    private final String name;
    private final Category category;
    private BigDecimal price;
    private BigDecimal previousPrice;
    private boolean hasChanged;

    public ProductRecord(UUID id, String name, Category category, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.previousPrice = price;
        this.hasChanged = false;
    }

    public Category getCategory() {
        return category;
    }

    public void setPrice(BigDecimal price) {
        this.previousPrice = this.price;
        this.price = (price != null) ? price : BigDecimal.ZERO;
        this.hasChanged = !this.price.equals(previousPrice);
    }

    public UUID uuid() {
        return id;
    }

    public BigDecimal price() {
        return price;
    }

    public boolean hasChanged() {
        return hasChanged;
    }

    public String name() {
        return name;
    }

    public Category category() {
        return category;
    }

    @Override
    public String toString() {
        return "ProductRecord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", previousPrice=" + previousPrice +
                ", hasChanged=" + hasChanged +
                '}';
    }
}
