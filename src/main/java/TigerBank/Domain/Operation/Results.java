package TigerBank.Domain.Operation;

/**
 * Класс для возврата результатов банковских операций
 */
public final class Results {

  private Results() {
  }

  public static OperationResult success() {
    return new SuccessResult();
  }

  public static OperationResult failure(String message) {
    return new FailureResult(message);
  }
}
