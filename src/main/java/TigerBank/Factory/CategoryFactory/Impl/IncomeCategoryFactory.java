package TigerBank.Factory.CategoryFactory.Impl;

import TigerBank.Domain.Category.Category;
import TigerBank.Domain.Category.CategoryParam.CategoryParam;
import TigerBank.Domain.Category.CategoryParam.IncomeCategoryParam;
import TigerBank.Domain.Category.IncomeCategory;
import TigerBank.Domain.TxType.TxType;
import TigerBank.Factory.CategoryFactory.CategoryFactory;
import org.springframework.stereotype.Component;

@Component
public class IncomeCategoryFactory implements CategoryFactory {

  @Override
  public Category createCategoryWithParam(CategoryParam param) {
    if (param instanceof IncomeCategoryParam incParam) {
      return new IncomeCategory(incParam.getId(), incParam.getType(), incParam.getName());
    } else {
      throw new IllegalArgumentException(String.format("Ожидался IncomeCategoryParam, получен: %s",
          param.getClass().getSimpleName()));
    }
  }

  @Override
  public boolean isCategorySupported(TxType type) {
    return type.equals(TxType.INCOME);
  }
}
