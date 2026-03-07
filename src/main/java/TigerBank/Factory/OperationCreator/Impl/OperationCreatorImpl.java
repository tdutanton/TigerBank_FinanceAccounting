package TigerBank.Factory.OperationCreator.Impl;

import TigerBank.Domain.Operation.Operation;
import TigerBank.Domain.TxType.TxType;
import TigerBank.Factory.OperationCreator.OperationCreator;
import TigerBank.Factory.OperationFactory.OperationFactory;
import TigerBank.Utils.Logging.Logger;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationCreatorImpl implements OperationCreator {

  private final OperationFactory factory;
  private final Logger logger;

  @Override
  public Operation createOperation(String id, TxType type, String bankAccountId,
      long amount, LocalDateTime date, String categoryId,
      String description) {
    try {
      return factory.create(id, type, bankAccountId,
          amount, date, categoryId, description);
    } catch (IllegalArgumentException e) {
      logger.info("Ошибка создания операции: " + e.getMessage());
      throw e;
    }
  }

  @Override
  public Operation createOperation(String id, TxType type, String bankAccountId,
      long amount, LocalDateTime date, String categoryId) {
    try {
      return factory.create(id, type, bankAccountId,
          amount, date, categoryId, null);
    } catch (IllegalArgumentException e) {
      logger.info("Ошибка создания операции: " + e.getMessage());
      throw e;
    }
  }
}
