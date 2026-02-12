package Presentation;

import Domain.TxType.TxType;
import java.util.Map;

public class TxTypeDisplay {

  private static final Map<TxType, String> DISPLAY_NAMES = Map.of(
      TxType.INCOME, "Доход",
      TxType.EXPENSE, "Расход"
  );

  private TxTypeDisplay() {
  }

  public static String getDisplayName(TxType type) {
    return DISPLAY_NAMES.get(type);
  }
}
