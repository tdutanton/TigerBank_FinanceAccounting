package TigerBank.Analytics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString(includeFieldNames = true)
@Getter
@AllArgsConstructor
public class CategorySummary {

  private final String categoryId;
  private long income;
  private long expense;

  public void addIncome(long amount) {
    this.income += amount;
  }

  public void addExpense(long amount) {
    this.expense += amount;
  }

  public long getNet() {
    return income - expense;
  }
}