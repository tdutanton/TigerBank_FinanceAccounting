package TigerBank.Factory.CategoryFactory;

import TigerBank.Domain.Category.Category;
import TigerBank.Domain.Category.CategoryParam.CategoryParam;
import TigerBank.Domain.TxType.TxType;

public interface CategoryFactory {

  Category createCategoryWithParam(CategoryParam param);

  boolean isCategorySupported(TxType type);
}
