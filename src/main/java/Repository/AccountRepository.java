package Repository;

import Domain.Account.BankAccount;
import Utils.Logging.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * String - id счета
 */
public class AccountRepository {
    private final Map<String, BankAccount> accounts;
    private final Logger logger;

    public AccountRepository(Logger logger) {
        accounts = new HashMap<>();
        this.logger = logger;
    }

    private boolean isInRepository(BankAccount account) {
        return accounts.containsKey(account.getId());
    }

    private boolean isInRepository(String id) {
        return accounts.containsKey(id);
    }

    private void safetyAddAccount(BankAccount account) {
        if (!isInRepository(account)) {
            accounts.put(account.getId(), account);
            logger.info("Сохранен счет: %n", account.getId());
            return;
        }
        logger.info("Счет %s уже существует в хранилище.%n", account.getId());
    }

    public void addAccount(BankAccount account) {
        safetyAddAccount(account);
    }
}
