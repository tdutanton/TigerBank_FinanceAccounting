package TigerBank.Repository;

import TigerBank.Domain.Account.Account;
import TigerBank.Utils.Logging.Logger;
import org.springframework.stereotype.Component;

/**
 * Хранилище счетов. String - id счета
 */
@Component
public class AccountRepository extends BaseRepository<Account> {

  public AccountRepository(Logger logger) {
    super(logger);
  }

  @Override
  public boolean exists(Account account) {
    return storage.containsKey(account.getId());
  }

  @Override
  protected void safetyAdd(Account account) {
    if (!exists(account)) {
      storage.put(account.getId(), account);
      logger.info("Сохранен счет в репозиторий: " + account.getId());
      return;
    }
    logger.info("Счет " + account.getId() + " уже существует в хранилище");
  }

  @Override
  protected void safetyDelete(Account account) {
    if (exists(account)) {
      storage.remove(account.getId());
      logger.info("Удален счет из репозитория: " + account.getId());
      return;
    }
    logger.info("Счет " + account.getId() + " не существует в хранилище");
  }

  @Override
  protected void safetyUpdate(Account account) {
    if (exists(account)) {
      storage.put(account.getId(), account);
      logger.info("Обновлен (изменен) счет в репозитории: " + account.getId());
      return;
    }
    logger.info("Счет " + account.getId() + " не существует в хранилище");
  }
}
