package TigerBank.Service;

import TigerBank.Domain.Category.Category;
import TigerBank.Domain.TxType.TxType;
import TigerBank.Repository.CategoryRepository;
import TigerBank.Utils.Logging.Logger;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

  @Getter
  private final CategoryRepository repository;
  private final Logger logger;

  public void createAndSaveCategory(String id, TxType type, String name) {
    try {
      Category category = new Category(id, type, name);
      repository.add(category);
    } catch (IllegalArgumentException e) {
      logger.info(e.toString());
    }
  }

  public void deleteCategory(String name) {
    Optional<Category> category = repository.findByKey(name);
    category.ifPresent(repository::delete);
  }

  public String categoryId(String name) {
    return repository.findByKey(name)
        .map(Category::getId)
        .orElse(null);
  }

  public boolean exists(String name) {
    return repository.findByKey(name)
        .map(repository::exists)
        .orElse(false);
  }
}
