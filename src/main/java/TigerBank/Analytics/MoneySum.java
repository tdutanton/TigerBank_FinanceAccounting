package TigerBank.Analytics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(includeFieldNames = true)
@AllArgsConstructor
@Getter
@Setter
public class MoneySum {

  private long income;
  private long expense;
  private long difference;
}
