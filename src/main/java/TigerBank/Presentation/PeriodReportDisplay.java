package TigerBank.Presentation;

import TigerBank.Analytics.MoneySum;
import java.time.LocalDateTime;

public class PeriodReportDisplay {

  private PeriodReportDisplay() {
  }

  public static void PrintPeriodReport(LocalDateTime start, LocalDateTime end, MoneySum result) {
    System.out.println("Начало периода: " + start);
    System.out.println("Конец периода: " + start);
    System.out.println("Отчет");
    System.out.println("Доходы: " + result.getIncome());
    System.out.println("Расходы: " + result.getExpense());
    System.out.println("Баланс: " + result.getDifference());
  }

}
