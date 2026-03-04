package TigerBank.Factory.CategoryCreator.Impl;

import TigerBank.Domain.Category.Category;
import TigerBank.Domain.Category.CategoryParam.CategoryParam;
import TigerBank.Factory.CategoryCreator.CategoryCreator;
import TigerBank.Factory.CategoryFactory.CategoryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryCreatorImpl implements CategoryCreator {

  private final List<CategoryFactory> factories;

  @Override
  public Category createCategory(CategoryParam param) {
    for (CategoryFactory factory : factories) {
      if (factory.isCategorySupported(param.getType())) {
        try {
          return factory.createCategoryWithParam(param);
        } catch (IllegalArgumentException e) {
          System.out.printf("Ошибка создания категории: %s%n", e);
        }
      }
    }
    throw new IllegalArgumentException(
        String.format("Тип категории %s не поддерживается сервисом",
            param.getType()));
  }
}
