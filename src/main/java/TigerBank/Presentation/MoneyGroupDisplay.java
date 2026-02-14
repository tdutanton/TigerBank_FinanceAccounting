package TigerBank.Presentation;

import TigerBank.Analytics.MoneyGroup.Summary;

public class MoneyGroupDisplay {

  private MoneyGroupDisplay() {
  }

  public static void PrintMoneyGroup(Summary result) {
    System.out.println("Отчет");
    System.out.println("Доходы: " + result.totalIncome());
    System.out.println("Расходы: " + result.totalExpense());
  }
}
