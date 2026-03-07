package TigerBank.Analytics;

import TigerBank.Domain.Operation.Operation;
import TigerBank.Domain.TxType.TxType;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class MoneyGroup implements Calculatable {

  @Getter
  private final List<Operation> operations;

  @Getter
  private final Map<String, CategorySummary> result = new LinkedHashMap<>();

  @Override
  public void calculate() {
    result.clear();

    for (Operation operation : operations) {
      String categoryId = operation.getCategoryId();
      CategorySummary summary = result.computeIfAbsent(
          categoryId,
          id -> new CategorySummary(id, 0L, 0L)
      );
      if (operation.getType() == TxType.INCOME) {
        summary.addIncome(operation.getAmount());
      } else if (operation.getType() == TxType.EXPENSE) {
        summary.addExpense(operation.getAmount());
      }
    }
  }

  public Summary getTotalSummary() {
    long totalIncome = result.values().stream().mapToLong(CategorySummary::getIncome).sum();
    long totalExpense = result.values().stream().mapToLong(CategorySummary::getExpense).sum();
    return new Summary(totalIncome, totalExpense);
  }

  public record Summary(long totalIncome, long totalExpense) {

    public long getNet() {
      return totalIncome - totalExpense;
    }
  }
}