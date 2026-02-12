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
public class AccountRepository extends BaseRepository<Account> {
    public AccountRepository(Logger logger) {
        super(logger);
    }
}

/*@Component
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
            logger.info("Сохранен счет в репозиторий: " + account.getId());
            return;
        }
        logger.info("Счет " + account.getId() + " уже существует в хранилище");
    }

    public void addAccount(Account account) {
        safetyAddAccount(account);
    }

    private void safetyDeleteAccount(Account account) {
        if (isInRepository(account)) {
            accounts.remove(account.getId());
            logger.info("Удален счет из репозитория: " + account.getId());
            return;
        }
        logger.info("Счет " + account.getId() + " не существует в хранилище");
    }

    public void deleteAccount(Account account) {
        safetyDeleteAccount(account);
    }

    public void safetyUpdateAccount(Account account) {
        if (isInRepository(account)) {
            accounts.put(account.getId(), account);
            logger.info("Обновлен (изменен) счет в репозитории: " + account.getId());
            return;
        }
        logger.info("Счет " + account.getId() + " не существует в хранилище");
    }

    public void updateAccount(Account account) {
        safetyUpdateAccount(account);
    }

    public Account accountById(String id) {
        Objects.requireNonNull(id, "Счета с id " + id + " не существует");
        return accounts.get(id);
    }
}*/
