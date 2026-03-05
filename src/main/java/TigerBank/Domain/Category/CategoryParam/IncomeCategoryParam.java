package TigerBank.Domain.Category.CategoryParam;

import TigerBank.Domain.TxType.TxType;
import lombok.Getter;

@Getter
public class IncomeCategoryParam extends CategoryParam {

  private final TxType type;

  public IncomeCategoryParam(String id, String name) {
    super(id, name);
    type = TxType.INCOME;
  }

  @Override
  public TxType getType() {
    return TxType.INCOME;
  }
}
