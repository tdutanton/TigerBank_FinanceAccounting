package TigerBank.Factory.CategoryCreator.Impl;

import TigerBank.Domain.Category.Category;
import TigerBank.Domain.Category.CategoryParam.CategoryParam;
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
  public Category createCategory(CategoryParam param) {
    if (!factory.isCategorySupported(param.getType())) {
      throw new IllegalArgumentException("Тип " + param.getType() + " не поддерживается");
    }
    try {
      return factory.createCategoryWithParam(param.getId(), param.getType(), param.getName());
    } catch (IllegalArgumentException e) {
      logger.info("Ошибка создания категории: " + e.getMessage());
      throw e;
    }
  }
}
