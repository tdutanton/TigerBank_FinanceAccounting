package TigerBank.Service;

import TigerBank.Analytics.DifIncomeExpense;
import TigerBank.Analytics.MoneyGroup;
import TigerBank.Analytics.MoneyGroup.Summary;
import TigerBank.Analytics.MoneySum;
import TigerBank.Domain.Operation.Operation;
import TigerBank.Domain.TxType.TxType;
import TigerBank.Factory.OperationFactory.OperationFactory;
import TigerBank.Repository.OperationRepository;
import TigerBank.Utils.Logging.Logger;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OperationService {

  @Getter
  private final OperationRepository repository;
  private final OperationFactory factory;
  private final Logger logger;

  public void createAndSaveOperation(String id,
      TxType type,
      String bankAccountId,
      long amount,
      LocalDateTime date,
      String categoryId,
      String description) {
    try {
      Operation operation = factory.create(id, type, bankAccountId, amount, date, categoryId,
          description);
      repository.add(operation);
    } catch (IllegalArgumentException e) {
      logger.info(e.toString());
    }
  }

  public void createAndSaveOperation(String id,
      TxType type,
      String bankAccountId,
      long amount,
      LocalDateTime date,
      String categoryId) {
    try {
      Operation operation = factory.create(id, type, bankAccountId, amount, date, categoryId);
      repository.add(operation);
    } catch (IllegalArgumentException e) {
      logger.info(e.toString());
    }
  }

  public void deleteOperation(String id) {
    Optional<Operation> operation = repository.findByKey(id);
    operation.ifPresent(repository::delete);
  }

  public MoneySum calculateDifferenceBetweenThreads(LocalDateTime start, LocalDateTime end) {
    DifIncomeExpense res = new DifIncomeExpense(repository.entities(), start, end);
    res.calculate();
    return res.getResult();
  }

  public Summary calculateMoneyGroup() {
    MoneyGroup res = new MoneyGroup(repository.entities());
    res.calculate();
    return res.getTotalSummary();
  }
}
