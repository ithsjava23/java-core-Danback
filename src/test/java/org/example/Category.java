package org.example;

import java.util.HashMap;
import java.util.Map;

public class Category {
    private static final Map<String, Category> categoryCache = new HashMap<>();

    private final String name;

    private Category(String name) {
        this.name = name;
    }

    public static Category of(String categoryName) {
        if (categoryName == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }

        String formattedName = formatCategoryName(categoryName);

        return categoryCache.computeIfAbsent(formattedName, Category::new);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    private static String formatCategoryName(String categoryName) {
        return categoryName.substring(0, 1).toUpperCase() + categoryName.substring(1).toLowerCase();
    }
}
