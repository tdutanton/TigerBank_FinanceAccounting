package Domain.Operation;

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
