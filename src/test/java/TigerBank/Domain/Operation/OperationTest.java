package TigerBank.Domain.Operation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import TigerBank.Domain.TxType.TxType;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class OperationTest {

  private static final String VALID_ID = "op_1";
  private static final String VALID_ACCOUNT_ID = "acc_1";
  private static final String VALID_CATEGORY_ID = "cat_1";
  private static final LocalDateTime NOW = LocalDateTime.now();

  @Test
  void shouldCreateOperationWithDescription() {
    Operation op = new Operation(
        VALID_ID,
        TxType.INCOME,
        VALID_ACCOUNT_ID,
        1000,
        NOW,
        VALID_CATEGORY_ID,
        "Зарплата"
    );

    assertEquals(VALID_ID, op.getId());
    assertEquals(TxType.INCOME, op.getType());
    assertEquals(1000, op.getAmount());
    assertEquals("Зарплата", op.getDescription());
  }

  @Test
  void shouldCreateOperationWithoutDescription() {
    Operation op = new Operation(
        VALID_ID,
        TxType.EXPENSE,
        VALID_ACCOUNT_ID,
        500,
        NOW,
        VALID_CATEGORY_ID
    );

    assertNull(op.getDescription()); // description = null по умолчанию
  }

  @Test
  void shouldConvertEmptyDescriptionToNull() {
    Operation op = new Operation(
        VALID_ID,
        TxType.EXPENSE,
        VALID_ACCOUNT_ID,
        500,
        NOW,
        VALID_CATEGORY_ID,
        ""
    );

    assertNull(op.getDescription()); // пустая строка → null
  }

  @Test
  void shouldThrowExceptionWhenAmountIsNegative() {
    assertThrows(IllegalArgumentException.class, () ->
        new Operation(
            VALID_ID,
            TxType.EXPENSE,
            VALID_ACCOUNT_ID,
            -100,  // отрицательная сумма
            NOW,
            VALID_CATEGORY_ID
        )
    );
  }

  @Test
  void shouldThrowExceptionWhenDateIsNull() {
    assertThrows(IllegalArgumentException.class, () ->
        new Operation(
            VALID_ID,
            TxType.INCOME,
            VALID_ACCOUNT_ID,
            1000,
            null,  // null дата
            VALID_CATEGORY_ID
        )
    );
  }

  @Test
  void shouldHaveCorrectToString() {
    Operation op = new Operation(
        VALID_ID,
        TxType.INCOME,
        VALID_ACCOUNT_ID,
        1000,
        NOW,
        VALID_CATEGORY_ID,
        "Тест"
    );
    String str = op.toString();
    assertTrue(str.contains("id=op_1"));
    assertTrue(str.contains("type=INCOME"));
    assertTrue(str.contains("amount=1000"));
  }
}