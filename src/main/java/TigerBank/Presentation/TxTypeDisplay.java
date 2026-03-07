package TigerBank.Presentation;

import TigerBank.Domain.TxType.TxType;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TxTypeDisplay implements Printable {

  private static final Map<TxType, String> DISPLAY_NAMES = Map.of(
      TxType.INCOME, "Доход",
      TxType.EXPENSE, "Расход"
  );
  private TxType type;

  public static String getDisplayName(TxType type) {
    return DISPLAY_NAMES.get(type);
  }

  @Override
  public void print() {
    System.out.println(getDisplayName(type));
  }
}
