package TigerBank.Factory.CategoryFactory.Impl;

import TigerBank.Domain.Category.Category;
import TigerBank.Domain.Category.CategoryParam.CategoryParam;
import TigerBank.Domain.Category.CategoryParam.ExpenseCategoryParam;
import TigerBank.Domain.Category.IncomeCategory;
import TigerBank.Domain.TxType.TxType;
import TigerBank.Factory.CategoryFactory.CategoryFactory;
import org.springframework.stereotype.Component;

@Component
public class ExpenseCategoryFactory implements CategoryFactory {

  @Override
  public Category createCategoryWithParam(CategoryParam param) {
    if (param instanceof ExpenseCategoryParam expParam) {
      return new IncomeCategory(expParam.getId(), expParam.getType(), expParam.getName());
    } else {
      throw new IllegalArgumentException(String.format("Ожидался ExpenseCategoryParam, получен: %s",
          param.getClass().getSimpleName()));
    }
  }

  @Override
  public boolean isCategorySupported(TxType type) {
    return type.equals(TxType.EXPENSE);
  }
}
