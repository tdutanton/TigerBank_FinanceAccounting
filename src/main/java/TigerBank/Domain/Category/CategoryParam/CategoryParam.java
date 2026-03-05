package TigerBank.Domain.Category.CategoryParam;

import TigerBank.Domain.TxType.TxType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class CategoryParam {

  protected String id;
  protected String name;

  public abstract TxType getType();
}
