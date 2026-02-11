package Domain.Account;

import Domain.Operation.OperationResult;

public interface Depositable {
    OperationResult deposit(long amount);
}
