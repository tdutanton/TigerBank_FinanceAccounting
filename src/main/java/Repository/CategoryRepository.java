package Repository;

import Domain.Category.Category;

import java.util.HashMap;
import java.util.Map;

/**
 * String - название категории
 */
public class CategoryRepository {
    private final Map<String, Category> categories;

    public CategoryRepository() {
        categories = new HashMap<>();
    }
}