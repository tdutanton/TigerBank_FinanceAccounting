package Domain.Account;

import Domain.Operation.OperationResult;
import Domain.Operation.Results;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@ToString(includeFieldNames=true)
public final class BankAccount extends Account implements Withdrawable, Depositable{
    public BankAccount(String id, String name, long balance) {
        super(id, name, balance);
    }

    public BankAccount(String id, String name) {
        super(id, name);
    }

    @Override
    public OperationResult withdraw(long amount) {
        if (!isAmountCorrect(amount)) {
            return Results.failure("Сумма снятия должна быть положительной");
        }
        if (!isWithdrawPossible(amount)) {
            return Results.failure("Недостаточно средств на счёте");
        }
        this.balance -= amount;
        return Results.success();
    }

    @Override
    public OperationResult deposit(long amount) {
        if (!isAmountCorrect(amount)) {
            return Results.failure("Сумма депозита должна быть корректной");
        }
        this.balance += amount;
        return Results.success();
    }

    private boolean isAmountCorrect(long amount) {
        return amount >= 0;
    }

    private boolean isWithdrawPossible(long amount) {
        return this.balance >= amount;
    }

    @Override
    Account withName(String newName) {
        return new BankAccount(getId(), newName, this.balance);
    }

    @Override
    Account withBalance(long newBalance) {
        return new BankAccount(getId(), this.name, newBalance);
    }
}
