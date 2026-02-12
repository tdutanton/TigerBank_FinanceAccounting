package Repository;

import Domain.Category.Category;
import Utils.Logging.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * String - название категории
 */
@Component
public class CategoryRepository extends BaseRepository<Category> {
    public CategoryRepository(Logger logger) {
        super(logger);
    }
}