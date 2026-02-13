package TigerBank.Domain.Operation;

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
