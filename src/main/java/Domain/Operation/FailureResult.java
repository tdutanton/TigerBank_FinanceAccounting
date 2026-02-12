package Domain.Operation;

public record FailureResult(String message) implements OperationResult {

  @Override
  public boolean isSuccess() {
    return false;
  }
}
