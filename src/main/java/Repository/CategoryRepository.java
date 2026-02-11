package Repository;

import Domain.Category.Category;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * String - название категории
 */
@Component
public class CategoryRepository {
    private final Map<String, Category> categories;

    public CategoryRepository() {
        categories = new HashMap<>();
    }
}