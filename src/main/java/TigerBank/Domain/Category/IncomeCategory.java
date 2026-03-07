package TigerBank.Domain.Category;

import TigerBank.Domain.TxType.TxType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true, includeFieldNames = false)
public class IncomeCategory extends Category {

  public IncomeCategory(String id, String name) {
    super(id, TxType.INCOME, name);
  }
}
