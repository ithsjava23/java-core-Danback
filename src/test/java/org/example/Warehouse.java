package org.example;
//
import java.math.BigDecimal;
import java.util.*;

public class Warehouse {
    private static Warehouse instance;
    private final Map<UUID, ProductRecord> products;
    private final String name;

    private Warehouse(String name) {
        this.name = name;
        this.products = new HashMap<>();
    }

    public static Warehouse getInstance(String name) {
        return new Warehouse(name);
    }

    public static Warehouse getInstance() {
        return new Warehouse("");
    }

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
        if (price == null) {
            price = BigDecimal.ZERO;
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
    //
        return Collections.emptyList();
    }

    public List<ProductRecord> getChangedProducts() {
        List<ProductRecord> changedProducts = new ArrayList<>();
        for (ProductRecord product : products.values()) {
            if (product.hasChanged()) {
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
            if (product.getCategory().equals(category)) {
                result.add(product);
            }
        }
        return result;
    }

    public Map<Category, List<ProductRecord>> getProductsGroupedByCategories() {
        Map<Category, List<ProductRecord>> groupedProducts = new HashMap<>();
        for (ProductRecord product : products.values()) {
            Category category = product.getCategory();
            groupedProducts.computeIfAbsent(category, k -> new ArrayList<>()).add(product);
        }
        return groupedProducts;
    }
}
