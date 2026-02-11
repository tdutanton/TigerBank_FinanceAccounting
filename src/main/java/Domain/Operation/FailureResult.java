package Domain.Operation;

public final class FailureResult implements OperationResult {
    private final String message;

    public FailureResult(String message) {
        this.message = message;
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public String message() {
        return message;
    }
}
