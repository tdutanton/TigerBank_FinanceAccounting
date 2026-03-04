package TigerBank.Domain.Category.CategoryParam;

import TigerBank.Domain.TxType.TxType;
import lombok.Getter;

@Getter
public class ExpenseCategoryParam extends CategoryParam {

  public ExpenseCategoryParam(String id, TxType type, String name) {
    super(id, type, name);
  }
}
