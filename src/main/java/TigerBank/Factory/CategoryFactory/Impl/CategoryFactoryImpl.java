package TigerBank.Factory.CategoryFactory.Impl;

import TigerBank.Domain.Category.Category;
import TigerBank.Domain.Category.ExpenseCategory;
import TigerBank.Domain.Category.IncomeCategory;
import TigerBank.Domain.TxType.TxType;
import TigerBank.Factory.CategoryFactory.CategoryFactory;
import org.springframework.stereotype.Component;

@Component
public class CategoryFactoryImpl implements CategoryFactory {

  @Override
  public Category createCategoryWithParam(String id, TxType type, String name) {
    return switch (type) {
      case EXPENSE -> new ExpenseCategory(id, name);
      case INCOME -> new IncomeCategory(id, name);
      default -> throw new IllegalArgumentException("Неподдерживаемый тип: " + type);
    };
  }

  @Override
  public boolean isCategorySupported(TxType type) {
    return type == TxType.EXPENSE || type == TxType.INCOME;
  }
}