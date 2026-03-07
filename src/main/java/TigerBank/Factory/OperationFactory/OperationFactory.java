package TigerBank.Factory.OperationFactory;


import TigerBank.Domain.Operation.Operation;
import TigerBank.Domain.TxType.TxType;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class OperationFactory {

  public Operation create(String id, TxType type, String bankAccountId,
      long amount, LocalDateTime date, String categoryId) {
    return create(id, type, bankAccountId, amount, date, categoryId, null);
  }

  public Operation create(String id, TxType type, String bankAccountId,
      long amount, LocalDateTime date, String categoryId,
      String description) {
    validateRequired(id, type, bankAccountId, amount, date, categoryId);
    String safeDescription = (description == null || description.isBlank())
        ? null
        : description;

    return new Operation(id, type, bankAccountId, amount, date, categoryId, safeDescription);
  }

  private void validateRequired(String id, TxType type, String bankAccountId,
      long amount, LocalDateTime date, String categoryId) {
    if (id == null || id.isBlank()) {
      throw new IllegalArgumentException("ID операции не может быть пустым");
    }
    if (type == null) {
      throw new IllegalArgumentException("Тип операции не может быть null");
    }
    if (bankAccountId == null || bankAccountId.isBlank()) {
      throw new IllegalArgumentException("ID счёта не может быть пустым");
    }
    if (amount < 0) {
      throw new IllegalArgumentException("Сумма не может быть отрицательной");
    }
    if (date == null) {
      throw new IllegalArgumentException("Дата операции обязательна");
    }
    if (categoryId == null || categoryId.isBlank()) {
      throw new IllegalArgumentException("ID категории не может быть пустым");
    }
  }
}