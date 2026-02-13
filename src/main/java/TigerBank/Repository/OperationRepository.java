package TigerBank.Repository;

import TigerBank.Domain.Operation.Operation;
import TigerBank.Utils.Logging.Logger;
import org.springframework.stereotype.Component;

/**
 * String - id операции
 */
@Component
public class OperationRepository extends BaseRepository<Operation> {

  public OperationRepository(Logger logger) {
    super(logger);
  }

  @Override
  public boolean exists(Operation operation) {
    return storage.containsKey(operation.getId());
  }

  @Override
  protected void safetyAdd(Operation operation) {
    if (!exists(operation)) {
      storage.put(operation.getId(), operation);
      logger.info("Сохранена операция в репозиторий: " + operation.getId());
      return;
    }
    logger.info("Операция " + operation.getId() + " уже существует в хранилище");
  }

  @Override
  protected void safetyDelete(Operation operation) {
    if (exists(operation)) {
      storage.remove(operation.getId());
      logger.info("Удалена операция из репозитория: " + operation.getId());
      return;
    }
    logger.info("Операция " + operation.getId() + " не существует в хранилище");
  }

  @Override
  protected void safetyUpdate(Operation operation) {
    if (exists(operation)) {
      storage.put(operation.getId(), operation);
      logger.info("Обновлена (изменена) операция в репозитории: " + operation.getId());
      return;
    }
    logger.info("Операция " + operation.getId() + " не существует в хранилище");
  }
}
