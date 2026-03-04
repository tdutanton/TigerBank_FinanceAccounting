package TigerBank.Domain.Category;

import TigerBank.Domain.TxType.TxType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString(includeFieldNames = true)
public class ExpenseCategory extends Category {

  public ExpenseCategory(String id, TxType type, String name) {
    super(id, type, name);
  }
}
