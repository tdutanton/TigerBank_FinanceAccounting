package TigerBank.Domain.TxType;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TxTypeTest {

  @Test
  void shouldHaveIncomeAndExpenseValues() {
    assertEquals(2, TxType.values().length);
    assertEquals(TxType.INCOME, TxType.valueOf("INCOME"));
    assertEquals(TxType.EXPENSE, TxType.valueOf("EXPENSE"));
  }
}
