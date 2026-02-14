package TigerBank.Analytics;

import TigerBank.Domain.Operation.Operation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import lombok.Getter;

public class DifIncomeExpense implements Calculatable {

  private final ArrayList<Operation> operations;
  private final LocalDateTime start;
  private final LocalDateTime end;
  @Getter
  private MoneySum result = new MoneySum(0, 0, 0);

  public DifIncomeExpense(ArrayList<Operation> operations, LocalDateTime start,
      LocalDateTime end) {
    this.operations = operations;
    this.start = start;
    this.end = end;
  }

  @Override
  public void calculate() {
    this.result = new MoneySum(0, 0, 0);
    for (Operation operation : operations) {
      if (operation.getDate().isAfter(start) && operation.getDate().isBefore(end)) {
        switch (operation.getType()) {
          case INCOME -> this.result.setIncome(this.result.getIncome() + operation.getAmount());
          case EXPENSE -> this.result.setExpense(this.result.getExpense() + operation.getAmount());
        }
      }
    }
    this.result.setDifference(result.getIncome() - result.getExpense());
  }
}
