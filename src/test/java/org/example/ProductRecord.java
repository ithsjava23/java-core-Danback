package org.example;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductRecord {
    private final UUID id;
    private final String name;
    private final Category category;
    private BigDecimal price;
    private BigDecimal previousPrice; // Store the previous price

    public ProductRecord(UUID id, String name, Category category, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.previousPrice = price; // Initialize previousPrice with the current price
    }

    public Category getCategory() {
        return category;
    }

    public void setPrice(BigDecimal price) {
        this.previousPrice = this.price; // Update previousPrice before changing the price
        this.price = price;
    }

    public UUID uuid() {
        return id;
    }

    public BigDecimal price() {
        return price;
    }

    public boolean hasChanged() {
        return !price.equals(previousPrice); // Check if the current price is different from the previous price
    }

    public Category category() {
        return null;
    }
}



