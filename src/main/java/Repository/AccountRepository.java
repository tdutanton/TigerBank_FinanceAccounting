package Repository;

import Domain.Account.Account;
import Utils.Logging.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * String - id счета
 */
@Component
public class AccountRepository {
    private final Map<String, Account> accounts;
    private final Logger logger;

    public AccountRepository(Logger logger) {
        accounts = new HashMap<>();
        this.logger = logger;
    }

    private boolean isInRepository(Account account) {
        return accounts.containsKey(account.getId());
    }

    private boolean isInRepository(String id) {
        return accounts.containsKey(id);
    }

    private void safetyAddAccount(Account account) {
        if (!isInRepository(account)) {
            accounts.put(account.getId(), account);
            logger.info("Сохранен счет в репозиторий: %n");
            return;
        }
        logger.info("Счет %s уже существует в хранилище.%n");
    }

    public void addAccount(Account account) {
        safetyAddAccount(account);
    }

    private void safetyDeleteAccount(Account account) {
        if (isInRepository(account)) {
            accounts.remove(account.getId());
            logger.info("Удален счет из репозитория: %n");
            return;
        }
        logger.info("Счет %s не существует в хранилище.%n");
    }

    public void deleteAccount(Account account) {
        safetyDeleteAccount(account);
    }

    public void safetyUpdateAccount(Account account) {
        if (isInRepository(account)) {
            accounts.put(account.getId(), account);
            logger.info("Обновлен (изменен) счет в репозитории: %n");
            return;
        }
        logger.info("Счет %s не существует в хранилище.%n");
    }

    public void updateAccount(Account account) {
        safetyUpdateAccount(account);
    }

    public Account accountById(String id) {
        Objects.requireNonNull(id, "Счета с таким id не существует");
        return accounts.get(id);
    }
}
