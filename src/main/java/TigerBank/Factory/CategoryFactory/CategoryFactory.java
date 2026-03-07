package TigerBank.Factory.CategoryFactory;

import TigerBank.Domain.Category.Category;
import TigerBank.Domain.TxType.TxType;

public interface CategoryFactory {

  Category createCategoryWithParam(String id, TxType type, String name);
}
