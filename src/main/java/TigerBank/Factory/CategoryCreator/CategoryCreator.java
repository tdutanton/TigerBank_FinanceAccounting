package TigerBank.Factory.CategoryCreator;

import TigerBank.Domain.Category.Category;
import TigerBank.Domain.Category.CategoryParam.CategoryParam;

public interface CategoryCreator {

  Category createCategory(CategoryParam param);
}
