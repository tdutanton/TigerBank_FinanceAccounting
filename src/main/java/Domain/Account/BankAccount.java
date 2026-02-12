package Domain.Account;

import Domain.Identifiable.Identifiable;
import Domain.Operation.OperationResult;
import Domain.Operation.Results;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString(includeFieldNames=true)
public final class BankAccount extends Account implements Withdrawable, Depositable, Identifiable {
    public BankAccount(String id, String name, long balance) {
        super(id, name, balance);
    }

    public BankAccount(String id, String name) {
        super(id, name);
    }

    @Override
    public void withdraw(long amount) {
        this.balance -= amount;
    }

    @Override
    public void deposit(long amount) {
        this.balance += amount;
    }
}
