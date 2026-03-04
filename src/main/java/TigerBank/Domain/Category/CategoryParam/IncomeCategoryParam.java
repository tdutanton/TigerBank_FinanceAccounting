package TigerBank.Domain.Category.CategoryParam;

import TigerBank.Domain.TxType.TxType;
import lombok.Getter;

@Getter
public class IncomeCategoryParam extends CategoryParam {

  public IncomeCategoryParam(String id, TxType type, String name) {
    super(id, type, name);
  }
}
