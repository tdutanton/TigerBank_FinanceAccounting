package Service;

import Domain.Account.Account;
import Domain.Account.BankAccount;
import Domain.Account.Depositable;
import Domain.Account.Withdrawable;
import Domain.Operation.OperationResult;
import Domain.Operation.Results;
import Repository.AccountRepository;
import Utils.Logging.Logger;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {

  private final AccountRepository repository;
  private final Logger logger;

  public void createAndSaveBankAccount(String id, String name, long balance) {
    try {
      Account account = new BankAccount(id, name, balance);
      repository.add(account);
    } catch (IllegalArgumentException e) {
      logger.info(e.toString());
    }
  }

  public void createAndSaveBankAccount(String id, String name) {
    try {
      Account account = new BankAccount(id, name);
      repository.add(account);
    } catch (IllegalArgumentException e) {
      logger.info(e.toString());
    }
  }

  public void deleteBankAccount(String id) {
    Optional<Account> account = repository.findByKey(id);
    account.ifPresent(repository::delete);
  }

  private boolean isAmountCorrect(long amount) {
    return amount >= 0;
  }

  private boolean isWithdrawPossible(Withdrawable account, long amount) {
    return account.getBalance() >= amount;
  }

  public OperationResult withdraw(Withdrawable account, long amount) {
    if (!isAmountCorrect(amount)) {
      return Results.failure("Сумма снятия должна быть положительной");
    }
    if (!isWithdrawPossible(account, amount)) {
      return Results.failure("Недостаточно средств на счёте");
    }
    account.withdraw(amount);
    logger.info("Со счета снята сумма: " + amount / 100.0);
    repository.update((Account) account);
    return Results.success();
  }

  public OperationResult withdrawById(String id, long amount) {
    Optional<Account> account = repository.findByKey(id);
    if (account.isEmpty()) {
      return Results.failure("Счёт не найден");
    }
    return withdraw((Withdrawable) account.get(), amount);
  }

  public OperationResult deposit(Depositable account, long amount) {
    if (!isAmountCorrect(amount)) {
      return Results.failure("Сумма депозита должна быть корректной");
    }
    account.deposit(amount);
    logger.info("Счет пополнен на сумму: " + amount / 100.0);
    repository.update((Account) account);
    return Results.success();
  }

  public OperationResult depositById(String id, long amount) {
    Optional<Account> account = repository.findByKey(id);
    if (account.isEmpty()) {
      return Results.failure("Счёт не найден");
    }
    return deposit((Depositable) account.get(), amount);
  }
}
