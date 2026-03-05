package TigerBank.Domain.Category.CategoryParam;

import TigerBank.Domain.TxType.TxType;
import lombok.Getter;

@Getter
public class ExpenseCategoryParam extends CategoryParam {

  private final TxType type;

  public ExpenseCategoryParam(String id, String name) {
    super(id, name);
    type = TxType.EXPENSE;
  }

  @Override
  public TxType getType() {
    return TxType.EXPENSE;
  }
}
