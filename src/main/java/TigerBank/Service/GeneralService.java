package TigerBank.Service;

import TigerBank.Domain.TxType.TxType;
import TigerBank.Utils.IDGenerator.IDGenerator;
import TigerBank.Utils.Logging.Logger;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GeneralService {

  private AccountService accountService;
  private CategoryService categoryService;
  private OperationService operationService;

  private IDGenerator accountIdGenerator;
  private IDGenerator operationIdGenerator;
  private IDGenerator categoryIdGenerator;

  private Logger logger;

  public void createAndSaveBankAccount(String name, long balance) {
    accountService.createAndSaveBankAccount(accountIdGenerator.nextId(), name, balance);
  }

  public void createAndSaveBankAccount(String name) {
    accountService.createAndSaveBankAccount(accountIdGenerator.nextId(), name);
  }

  public void deleteBankAccount(String id) {
    accountService.deleteBankAccount(id);
  }

  public void createAndSaveCategory(TxType type, String name) {
    categoryService.createAndSaveCategory(categoryIdGenerator.nextId(), type, name);
  }

  public void deleteCategory(String name) {
    categoryService.deleteCategory(name);
  }

  public void createAndSaveOperation(String id,
      TxType type,
      String bankAccountId,
      long amount,
      LocalDateTime date,
      String categoryId,
      String description) {
    operationService.createAndSaveOperation(id, type, bankAccountId, amount, date, categoryId,
        description);
  }

  public void createAndSaveOperation(String id,
      TxType type,
      String bankAccountId,
      long amount,
      LocalDateTime date,
      String categoryId) {
    operationService.createAndSaveOperation(id, type, bankAccountId, amount, date, categoryId);
  }

  public void deleteOperation(String id) {
    operationService.deleteOperation(id);
  }

  public void depositToAccount(String accId, long amount, String categoryName) {
    if (accountService.depositById(accId, amount).isSuccess() && categoryService.exists(
        categoryName)) {
      createAndSaveOperation(operationIdGenerator.nextId(), TxType.INCOME, accId, amount,
          LocalDateTime.now(), categoryService.categoryId(categoryName));
    } else {
      logger.info("Пополнение не удалось");
    }
  }

  public void depositToAccount(String accId, long amount, String categoryName, String description) {
    if (accountService.depositById(accId, amount).isSuccess() && categoryService.exists(
        categoryName)) {
      createAndSaveOperation(operationIdGenerator.nextId(), TxType.INCOME, accId, amount,
          LocalDateTime.now(), categoryService.categoryId(categoryName), description);
    } else {
      logger.info("Пополнение не удалось");
    }
  }

  public void withdrawFromAccount(String accId, long amount, String categoryName) {
    if (accountService.withdrawById(accId, amount).isSuccess() && categoryService.exists(
        categoryName)) {
      createAndSaveOperation(operationIdGenerator.nextId(), TxType.EXPENSE, accId, amount,
          LocalDateTime.now(), categoryService.categoryId(categoryName));
    } else {
      logger.info("Снятие не удалось");
    }
  }

  public void withdrawFromAccount(String accId, long amount, String categoryName,
      String description) {
    if (accountService.withdrawById(accId, amount).isSuccess() && categoryService.exists(
        categoryName)) {
      createAndSaveOperation(operationIdGenerator.nextId(), TxType.EXPENSE, accId, amount,
          LocalDateTime.now(), categoryService.categoryId(categoryName), description);
    } else {
      logger.info("Снятие не удалось");
    }
  }
}