package org.example;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductRecord {
    private final UUID id;
    private final Category category;
    private BigDecimal price;
    private BigDecimal previousPrice;
    private boolean hasChanged; // Add a boolean flag to track changes

    public ProductRecord(UUID id, String name, Category category, BigDecimal price) {
        this.id = id;
        this.category = category;
        this.price = price;
        this.previousPrice = price; // Initialize previousPrice with the current price
        this.hasChanged = false; // Initialize to false
    }

    public Category getCategory() {
        return category;
    }

    public void setPrice(BigDecimal price) {
        this.previousPrice = this.price;
        this.price = (price != null) ? price : BigDecimal.ZERO;
        this.hasChanged = !this.price.equals(previousPrice); // Check for changes
    }

    public UUID uuid() {
        return id;
    }

    public BigDecimal price() {
        return price;
    }

    public boolean hasChanged() {
        return hasChanged; // Return the change status
    }

    public Category category() {
        return null;
    }
}




