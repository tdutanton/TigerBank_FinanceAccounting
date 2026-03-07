package TigerBank.Domain.Category;

import TigerBank.Domain.TxType.TxType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true, includeFieldNames = false)
public class ExpenseCategory extends Category {

  public ExpenseCategory(String id, String name) {
    super(id, TxType.EXPENSE, name);
  }
}
