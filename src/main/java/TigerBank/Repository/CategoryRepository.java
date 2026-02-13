package TigerBank.Repository;

import TigerBank.Domain.Category.Category;
import TigerBank.Utils.Logging.Logger;
import org.springframework.stereotype.Component;

/**
 * String - название категории
 */
@Component
public class CategoryRepository extends BaseRepository<Category> {

  public CategoryRepository(Logger logger) {
    super(logger);
  }

  @Override
  public boolean exists(Category category) {
    return storage.containsKey(category.getName());
  }

  @Override
  protected void safetyAdd(Category category) {
    if (!exists(category)) {
      storage.put(category.getName(), category);
      logger.info("Сохранена категория в репозиторий: " + category.getName());
      return;
    }
    logger.info("Категория " + category.getName() + " уже существует в хранилище");
  }

  @Override
  protected void safetyDelete(Category category) {
    if (exists(category)) {
      storage.remove(category.getName());
      logger.info("Удалена категория из репозитория: " + category.getName());
      return;
    }
    logger.info("Категория " + category.getName() + " не существует в хранилище");
  }

  @Override
  protected void safetyUpdate(Category category) {
    if (exists(category)) {
      storage.put(category.getName(), category);
      logger.info("Обновлена (изменена) категория в репозитории: " + category.getName());
      return;
    }
    logger.info("Категория " + category.getName() + " не существует в хранилище");
  }
}