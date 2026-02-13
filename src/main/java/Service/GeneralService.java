package Service;

import Domain.TxType.TxType;
import Utils.IDGenerator.IDGenerator;
import Utils.Logging.Logger;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GeneralService {

  private AccountService accountService;
  private CategoryService categoryService;
  private OperationService operationService;

  private IDGenerator accountIdGenerator;
  private IDGenerator operationIdGenerator;
  private IDGenerator categoryIdGenerator;

  private Logger logger;

  public void createAndSaveBankAccount(String id, String name, long balance) {
    accountService.createAndSaveBankAccount(id, name, balance);
  }

  public void createAndSaveBankAccount(String id, String name) {
    accountService.createAndSaveBankAccount(id, name);
  }

  public void deleteBankAccount(String id) {
    accountService.deleteBankAccount(id);
  }

  public void createAndSaveCategory(String id, TxType type, String name) {
    categoryService.createAndSaveCategory(id, type, name);
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


}