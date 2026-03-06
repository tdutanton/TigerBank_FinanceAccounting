package TigerBank.Factory.CategoryCreator.Impl;

import TigerBank.Domain.Category.Category;
import TigerBank.Domain.TxType.TxType;
import TigerBank.Factory.CategoryCreator.CategoryCreator;
import TigerBank.Factory.CategoryFactory.CategoryFactory;
import TigerBank.Utils.Logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryCreatorImpl implements CategoryCreator {

  private final CategoryFactory factory;
  private final Logger logger;

  @Override
  public Category createCategory(String id, TxType type, String name) {
    try {
      return factory.createCategoryWithParam(id, type, name);
    } catch (IllegalArgumentException e) {
      logger.info("Ошибка создания категории: " + e.getMessage());
      throw e;
    }
  }
}
