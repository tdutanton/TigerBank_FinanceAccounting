package TigerBank.Factory.CategoryCreator;

import TigerBank.Domain.Category.Category;
import TigerBank.Domain.TxType.TxType;

public interface CategoryCreator {

  Category createCategory(String id, TxType type, String name);
}
