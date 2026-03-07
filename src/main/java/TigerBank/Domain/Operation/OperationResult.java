package TigerBank.Domain.Operation;

/**
 * Интерфейс для результатов банковских операций
 */
public interface OperationResult {

  boolean isSuccess();

  String message();
}
