package TigerBank.Domain.Operation;

/**
 * Отрицательный результат банковской операции
 *
 * @param message
 */
public record FailureResult(String message) implements OperationResult {

  @Override
  public boolean isSuccess() {
    return false;
  }
}
