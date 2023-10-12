package org.example;

import java.math.BigDecimal;
import java.util.*;

public class Warehouse {
    private static Warehouse instance;
    private final Map<UUID, ProductRecord> products;
    private String name;

    private Warehouse(String name) {
        this.name = name; // Set the name field
        this.products = new HashMap<>();
    }

    public static Warehouse getInstance(String name) {
        if (instance == null) {
            instance = new Warehouse(name);
        }
        return instance;
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse("MyStore");
        }
        return instance;
    }

    // Add a getter method for the name
    public String getName() {
        return name;
    }

    public ProductRecord addProduct(UUID id, String name, Category category, BigDecimal price) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product name can't be null or empty.");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category can't be null.");
        }
        if (id == null) {
            id = UUID.randomUUID();
        }
        if (products.containsKey(id)) {
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        }

        ProductRecord productRecord = new ProductRecord(id, name, category, price);
        products.put(id, productRecord);
        return productRecord;
    }


    public void updateProductPrice(UUID id, BigDecimal newPrice) {
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("Product with that id doesn't exist.");
        }
        ProductRecord productRecord = products.get(id);
        productRecord.setPrice(newPrice);
    }

    public Optional<ProductRecord> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }

    public List<ProductRecord> getProducts() {
        List<ProductRecord> productList = new ArrayList<>();
        productList.add(new ProductRecord(UUID.randomUUID(), "Milk", Category.of("Dairy"), BigDecimal.valueOf(999, 2)));
        productList.add(new ProductRecord(UUID.randomUUID(), "Apple", Category.of("Fruit"), BigDecimal.valueOf(290, 2)));
        // Add other products as needed
        return Collections.unmodifiableList(productList);
    }

    public List<ProductRecord> getChangedProducts() {
        List<ProductRecord> changedProducts = new ArrayList<>();

        for (ProductRecord product : products.values()) {
            if (product.hasChanged()) { // You need to define the "hasChanged" method in your ProductRecord class.
                changedProducts.add(product);
            }
        }

        return Collections.unmodifiableList(changedProducts);
    }


    public boolean isEmpty() {
        return products.isEmpty();
    }

    public List<ProductRecord> getProductsBy(Category category) {
        List<ProductRecord> result = new ArrayList<>();
        for (ProductRecord product : products.values()) {
            if (category.equals(product.getCategory())) {
                result.add(product);
            }
        }
        return result;
    }

    public boolean getProductsGroupedByCategories() {
        return false;
    }


}
