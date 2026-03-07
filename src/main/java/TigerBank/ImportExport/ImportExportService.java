package TigerBank.ImportExport;

import TigerBank.Domain.Account.BankAccount;
import TigerBank.Domain.Category.Category;
import TigerBank.Domain.Operation.Operation;
import TigerBank.Factory.CategoryCreator.CategoryCreator;
import TigerBank.Repository.AccountRepository;
import TigerBank.Repository.CategoryRepository;
import TigerBank.Repository.OperationRepository;
import TigerBank.Utils.Logging.Logger;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ImportExportService {

  private final AccountRepository accountRepository;
  private final CategoryRepository categoryRepository;
  private final OperationRepository operationRepository;
  private final CategoryCreator categoryCreator;
  private final Logger logger;

  private final Map<String, DataFormat> formats;

  public ImportExportService(
      AccountRepository accountRepository,
      CategoryRepository categoryRepository,
      OperationRepository operationRepository, CategoryCreator categoryCreator,
      JsonFormat jsonFormat,
      YamlFormat yamlFormat,
      CsvFormat csvFormat,
      Logger logger) {
    this.accountRepository = accountRepository;
    this.categoryRepository = categoryRepository;
    this.operationRepository = operationRepository;
    this.categoryCreator = categoryCreator;
    this.logger = logger;

    this.formats = Map.of(
        "json", jsonFormat,
        "yaml", yamlFormat,
        "yml", yamlFormat,
        "csv", csvFormat
    );
  }

  public void exportAll(String format, String filename) throws IOException {
    DataFormat dataFormat = getFormat(format);
    Path filePath = getFilePath(filename, dataFormat.getFileExtension());

    BankDataDTO data = collectAllData();
    dataFormat.export(data, filePath);

    logger.info(String.format("Данные экспортированы в файл: %s", filePath));
  }

  public void importAll(String filepath) throws IOException {
    Path path = Paths.get(filepath);
    String extension = getFileExtension(path);

    DataFormat dataFormat = getFormat(extension);
    BankDataDTO importedData = dataFormat.importData(path);

    clearAllRepositories();

    importAccounts(importedData.getAccounts());
    importCategories(importedData.getCategories());
    importOperations(importedData.getOperations());

    logger.info(String.format("Данные импортированы из файла: %s", path));
  }

  private BankDataDTO collectAllData() {
    List<AccountDTO> accounts = accountRepository.entities().stream()
        .map(acc -> new AccountDTO(acc.getId(), acc.getName(), acc.getBalance()))
        .collect(Collectors.toList());

    List<CategoryDTO> categories = categoryRepository.entities().stream()
        .map(cat -> new CategoryDTO(cat.getId(), cat.getType(), cat.getName()))
        .collect(Collectors.toList());

    List<OperationDTO> operations = operationRepository.entities().stream()
        .map(op -> new OperationDTO(
            op.getId(),
            op.getType(),
            op.getBankAccountId(),
            op.getAmount(),
            op.getDate(),
            op.getCategoryId(),
            op.getDescription()
        ))
        .collect(Collectors.toList());

    return new BankDataDTO(accounts, categories, operations);
  }

  private void importAccounts(List<AccountDTO> accounts) {
    for (AccountDTO dto : accounts) {
      BankAccount account = new BankAccount(dto.getId(), dto.getName(), dto.getBalance());
      if (!accountRepository.exists(account)) {
        accountRepository.add(account);
      }
    }
    logger.info(String.format("Импортировано счетов: %d", accounts.size()));
  }

  private void importCategories(List<CategoryDTO> categories) {
    for (CategoryDTO dto : categories) {
      Category category = categoryCreator.createCategory(dto.getId(), dto.getType(), dto.getName());
      if (!categoryRepository.exists(category)) {
        categoryRepository.add(category);
      }
    }
    logger.info(String.format("Импортировано категорий: %d", categories.size()));
  }

  private void importOperations(List<OperationDTO> operations) {
    for (OperationDTO dto : operations) {
      Operation operation = new Operation(
          dto.getId(),
          dto.getType(),
          dto.getBankAccountId(),
          dto.getAmount(),
          dto.getDate(),
          dto.getCategoryId(),
          dto.getDescription()
      );
      if (!operationRepository.exists(operation)) {
        operationRepository.add(operation);
      }
    }
    logger.info(String.format("Импортировано операций: %d", operations.size()));
  }

  private void clearAllRepositories() {
    accountRepository.entities().forEach(accountRepository::delete);
    categoryRepository.entities().forEach(categoryRepository::delete);
    operationRepository.entities().forEach(operationRepository::delete);
  }

  private DataFormat getFormat(String format) {
    DataFormat dataFormat = formats.get(format.toLowerCase());
    if (dataFormat == null) {
      throw new IllegalArgumentException(
          "Неподдерживаемый формат: " + format +
              ". Поддерживаются: json, yaml, csv"
      );
    }
    return dataFormat;
  }

  private Path getFilePath(String filename, String extension) {
    if (!filename.contains(".")) {
      filename += extension;
    }

    Path exportDir = Paths.get("exports");
    if (!exportDir.toFile().exists()) {
      exportDir.toFile().mkdir();
    }

    return exportDir.resolve(filename);
  }

  private String getFileExtension(Path path) {
    String filename = path.getFileName().toString();
    int lastDot = filename.lastIndexOf('.');
    if (lastDot == -1) {
      throw new IllegalArgumentException("Файл не имеет расширения");
    }
    return filename.substring(lastDot + 1);
  }
}