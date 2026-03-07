package TigerBank.Factory.OperationCreator;

import TigerBank.Domain.Operation.Operation;
import TigerBank.Domain.TxType.TxType;
import java.time.LocalDateTime;

public interface OperationCreator {

  Operation createOperation(String id, TxType type, String bankAccountId,
      long amount, LocalDateTime date, String categoryId,
      String description);

  Operation createOperation(String id, TxType type, String bankAccountId,
      long amount, LocalDateTime date, String categoryId);
}
