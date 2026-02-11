package Domain.Account;

import Domain.Operation.OperationResult;

public interface Withdrawable {
    OperationResult withdraw(long amount);
}
