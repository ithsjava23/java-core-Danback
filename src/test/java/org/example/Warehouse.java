package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Warehouse {
    private static Warehouse instance;
    private String name;
    private Map<UUID, ProductRecord> products;

    // Privat konstruktor för att förhindra direkt skapande av Warehouse-objekt
    private Warehouse(String name) {
        this.name = name;
        this.products = new HashMap<>();
    }

    // Metod för att skapa eller hämta en befintlig Warehouse-instans
    public static Warehouse getInstance(String name) {
        if (instance == null) {
            instance = new Warehouse(name);
        }
        return instance;
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse("DefaultName");
        }
        return instance;
    }

    // Metod för att lägga till en produkt i lagret
    public ProductRecord addProduct(UUID id, String name, Category category, BigDecimal price) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product name can't be null or empty.");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category can't be null.");
        }
        if (id == null) {
            id = UUID.randomUUID(); // Generera ett nytt UUID om id är null
        }
        if (products.containsKey(id)) {
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        }

        ProductRecord productRecord = new ProductRecord(id, name, category, price);
        products.put(id, productRecord);
        return productRecord;
    }

    // Metod för att uppdatera priset på en produkt
    public void updateProductPrice(UUID id, BigDecimal newPrice) {
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("Product with that id doesn't exist.");
        }
        ProductRecord productRecord = products.get(id);
        productRecord.setPrice(newPrice);
    }

    // Metod för att hämta en produkt med ett visst ID
    public ProductRecord getProductById(UUID id) {
        return products.get(id);
    }

    // Metod för att hämta alla produkter
    public List<ProductRecord> getProducts() {
        return new ArrayList<>(products.values());
    }

    public boolean getChangedProducts() {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean getProductsBy(Category meat) {
        return false;
    }

    public boolean getProductsGroupedByCategories() {
        return false;
    }


}
