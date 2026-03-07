package TigerBank.Domain.Operation;

/**
 * Положительный результат банковской операции
 */
public final class SuccessResult implements OperationResult {

  @Override
  public boolean isSuccess() {
    return true;
  }

  @Override
  public String message() {
    return "OK";
  }
}
