package TigerBank.Interaction;

import TigerBank.Analytics.MoneyGroup.Summary;
import TigerBank.Analytics.MoneySum;
import TigerBank.Domain.Account.Account;
import TigerBank.Domain.Category.Category;
import TigerBank.Domain.Operation.Operation;
import TigerBank.Domain.TxType.TxType;
import TigerBank.ImportExport.ImportExportService;
import TigerBank.Presentation.AccountsDisplay;
import TigerBank.Presentation.CategoriesDisplay;
import TigerBank.Presentation.MoneyGroupDisplay;
import TigerBank.Presentation.OperationsDisplay;
import TigerBank.Presentation.PeriodReportDisplay;
import TigerBank.Presentation.TxTypeDisplay;
import TigerBank.Service.GeneralService;
import TigerBank.Utils.Logging.Logger;
import TigerBank.Utils.Stopwatch.Stopwatch;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.springframework.stereotype.Service;

@Service
public class Interaction {

  private static final String END_MSG = "< Конец сообщения >%n%n";
  private final Logger logger;
  private final GeneralService bank;
  private final Scanner scanner;
  private final Map<Integer, Runnable> menuActions;
  private final ImportExportService importExportService;

  public Interaction(Logger logger, GeneralService bank, Scanner scanner,
      ImportExportService importExportService) {
    this.logger = logger;
    this.bank = bank;
    this.scanner = scanner;
    this.importExportService = importExportService;
    this.menuActions = createMenuActions();
  }

  private Map<Integer, Runnable> createMenuActions() {
    Map<Integer, Runnable> map = new HashMap<>();
    map.put(1, this::createCustomer);
    map.put(2, this::createCategory);
    map.put(3, this::deposit);
    map.put(4, this::withdraw);
    map.put(5, this::showAccounts);
    map.put(6, this::showOperations);
    map.put(7, this::showCategories);
    map.put(8, this::showReportDates);
    map.put(9, this::showGroups);
    map.put(10, this::showImportExportMenu);
    return map;
  }

  public void showMenu() {
    System.out.println("--- МЕНЮ ---");
    System.out.println("Выберите действие: ");
    System.out.println("1.	Создать Счет");
    System.out.println("2.	Создать категорию");
    System.out.println("3.	Пополнить");
    System.out.println("4.	Потратить");
    System.out.println("5.	Показать счета");
    System.out.println("6.	Показать операции");
    System.out.println("7.	Показать категории");
    System.out.println("8.	Аналитика: Подсчет разницы доходов и расходов за выбранный период");
    System.out.println("9.	Аналитика: Группировка доходов и расходов по категориям");
    System.out.println("10.	Импорт / экспорт");
    System.out.println("0.	Выход");
  }

  private void showImportExportMenu() {
    boolean back = false;
    while (!back) {
      System.out.println("\n--- ИМПОРТ/ЭКСПОРТ ДАННЫХ ---");
      System.out.println("1. Экспорт в JSON");
      System.out.println("2. Экспорт в YAML");
      System.out.println("3. Экспорт в CSV");
      System.out.println("4. Импорт из файла");
      System.out.println("0. Назад");
      System.out.print("> ");

      int choice = getIntInput();
      try {
        switch (choice) {
          case 1 -> exportData("json");
          case 2 -> exportData("yaml");
          case 3 -> exportData("csv");
          case 4 -> importData();
          case 0 -> back = true;
          default -> logger.info("Неверный выбор");
        }
      } catch (Exception e) {
        logger.info("Ошибка: " + e.getMessage());
      }
    }
  }

  private void exportData(String format) {
    Stopwatch sw = Stopwatch.start("экспорт в файл");
    logger.info("Введите имя файла (без расширения): ");
    String filename = scanner.nextLine().trim();
    if (filename.isEmpty()) {
      logger.info("Имя файла не может быть пустым");
      return;
    }
    try {
      importExportService.exportAll(format, filename);
      System.out.printf(END_MSG);
    } catch (IOException e) {
      logger.info("Ошибка при экспорте: " + e.getMessage());
    }
    System.out.printf(END_MSG);
    sw.stop();
  }

  private void importData() {
    Stopwatch sw = Stopwatch.start("импорт из файла");
    logger.info("Введите путь к файлу (например: exports/data.json): ");
    String filepath = scanner.nextLine().trim();

    if (filepath.isEmpty()) {
      logger.info("Путь к файлу не может быть пустым");
      return;
    }
    logger.info("Внимание! Импорт перезапишет все существующие данные.");
    logger.info("Продолжить? (да/нет): ");
    String confirm = scanner.nextLine().trim().toLowerCase();
    if (confirm.equals("да") || confirm.equals("yes") || confirm.equals("y")) {
      try {
        importExportService.importAll(filepath);
        System.out.printf(END_MSG);
      } catch (IOException e) {
        logger.info("Ошибка при импорте: " + e.getMessage());
      }
    } else {
      logger.info("Импорт отменён");
    }
    System.out.printf(END_MSG);
    sw.stop();
  }


  public void runMenu() {
    boolean shouldExit = false;
    while (!shouldExit) {
      showMenu();
      int choice = getIntInput();
      if (choice == 0) {
        logger.info("Выход из консольного банка.");
        shouldExit = true;
      } else {
        Runnable action = menuActions.get(choice);
        if (action != null) {
          action.run();
        } else {
          logger.info("Неверный выбор. Попробуйте снова.\n");
        }
      }
    }
  }

  private int getIntInput() {
    while (!scanner.hasNextInt()) {
      logger.info("Пожалуйста, введите число: ");
      scanner.next();
    }
    int value = scanner.nextInt();
    scanner.nextLine();
    return value;
  }

  private double getDoubleInput() {
    while (!scanner.hasNextDouble()) {
      logger.info("Пожалуйста, введите корректную сумму: ");
      scanner.next();
    }
    double value = scanner.nextDouble();
    scanner.nextLine();
    return value;
  }

  private long getLongInput() {
    while (!scanner.hasNextLong()) {
      logger.info("Пожалуйста, введите корректную сумму: ");
      scanner.next();
    }
    long value = scanner.nextLong();
    scanner.nextLine();
    return value;
  }

  public void createCustomer() {
    Stopwatch sw = Stopwatch.start("создание счёта");
    logger.info("Введите имя счета: ");
    String name = scanner.nextLine();
    try {
      bank.createAndSaveBankAccount(name);
      logger.info("Создан счет: " + name);
      System.out.printf(END_MSG);
      sw.stop();
    } catch (IllegalArgumentException e) {
      logger.info(e.toString());
    }
  }

  public void createCategory() {
    Stopwatch sw = Stopwatch.start("создание категории");
    logger.info("Введите тип категории (доход, расход): ");
    String type = scanner.nextLine().trim().toLowerCase();
    TxType txType;
    switch (type) {
      case "доход" -> txType = TxType.INCOME;
      case "расход" -> txType = TxType.EXPENSE;
      default -> {
        logger.info(
            "Неизвестный тип категории '" + type + "'. Допустимые значения: 'доход', 'расход'");
        return;
      }
    }
    logger.info("Введите имя категории: ");
    String name = scanner.nextLine().trim();
    if (name.isEmpty()) {
      logger.info("Имя категории не может быть пустым");
      return;
    }
    bank.createAndSaveCategory(txType, name);
    logger.info(String.format("Категория '%s' (%s) создана",
        name, TxTypeDisplay.getDisplayName(txType)));
    System.out.printf(END_MSG);
    sw.stop();
  }

  public void deposit() {
    Stopwatch sw = Stopwatch.start("пополнение");
    logger.info("Введите номер счёта: ");
    String name = scanner.nextLine().trim();
    if (name.isEmpty()) {
      logger.info("Имя счёта не может быть пустым");
      System.out.printf(END_MSG);
      return;
    }
    logger.info("Введите сумму: ");
    long amount = getLongInput();
    logger.info("Введите категорию: ");
    String category = scanner.nextLine().trim();
    if (category.isEmpty()) {
      logger.info("Категория не может быть пустой");
      System.out.printf(END_MSG);
      return;
    }
    logger.info("Введите описание (опционально): ");
    String description = scanner.nextLine().trim();
    if (description.isEmpty()) {
      description = null;
    }
    try {
      if (description != null) {
        bank.depositToAccount(name, amount, category, description);
      } else {
        bank.depositToAccount(name, amount, category);
      }
      logger.info(String.format("Счёт '%s' пополнен на %.2f с категорией '%s'%s",
          name, amount / 100.0, category,
          description != null ? " и описанием: " + description : ""));
    } catch (IllegalArgumentException e) {
      logger.info("Ошибка: " + e.getMessage());
    }
    System.out.printf(END_MSG);
    sw.stop();
  }

  public void withdraw() {
    Stopwatch sw = Stopwatch.start("снятие");
    logger.info("Введите номер счёта: ");
    String name = scanner.nextLine().trim();
    if (name.isEmpty()) {
      logger.info("Имя счёта не может быть пустым");
      System.out.printf(END_MSG);
      return;
    }
    logger.info("Введите сумму: ");
    long amount = getLongInput();
    logger.info("Введите категорию: ");
    String category = scanner.nextLine().trim();
    if (category.isEmpty()) {
      logger.info("Категория не может быть пустой");
      System.out.printf(END_MSG);
      return;
    }
    logger.info("Введите описание (опционально): ");
    String description = scanner.nextLine().trim();
    if (description.isEmpty()) {
      description = null;
    }
    try {
      if (description != null) {
        bank.withdrawFromAccount(name, amount, category, description);
      } else {
        bank.withdrawFromAccount(name, amount, category);
      }
      logger.info(String.format("Со счета '%s' снято на %.2f с категорией '%s'%s",
          name, amount / 100.0, category,
          description != null ? " и описанием: " + description : ""));
    } catch (IllegalArgumentException e) {
      logger.info("Ошибка: " + e.getMessage());
    }
    System.out.printf(END_MSG);
    sw.stop();
  }

  public void showAccounts() {
    Stopwatch sw = Stopwatch.start("показать счета");
    logger.info("Текущие счета: ");
    ArrayList<Account> accounts = bank.accounts();
    AccountsDisplay.PrintAccounts(accounts);
    System.out.printf(END_MSG);
    sw.stop();
  }

  public void showOperations() {
    Stopwatch sw = Stopwatch.start("показать операции");
    logger.info("Операции: ");
    ArrayList<Operation> operations = bank.operations();
    OperationsDisplay.PrintOperations(operations);
    System.out.printf(END_MSG);
    sw.stop();
  }

  public void showCategories() {
    Stopwatch sw = Stopwatch.start("показать категории");
    logger.info("Категории: ");
    ArrayList<Category> categories = bank.categories();
    CategoriesDisplay.PrintCategories(categories);
    System.out.printf(END_MSG);
    sw.stop();
  }

  private LocalDateTime parseDateTime(String input) {
    input = input.trim();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    try {
      return LocalDateTime.parse(input, dateTimeFormatter);
    } catch (DateTimeParseException e) {
      try {
        LocalDate date = LocalDate.parse(input, dateFormatter);
        return date.atStartOfDay();
      } catch (DateTimeParseException ex) {
        throw new IllegalArgumentException(
            "Неверный формат даты. Используйте:\n" +
                "  • 'гггг-мм-дд' (например: 2026-02-14)\n" +
                "  • 'гггг-мм-дд чч:мм' (например: 2026-02-14 15:30)"
        );
      }
    }
  }

  private LocalDateTime adjustEndDate(LocalDateTime end, String rawInput) {
    if (rawInput.trim().length() == 10) {
      return end.toLocalDate().atTime(23, 59, 59, 999_999_999);
    }
    return end;
  }

  public void showReportDates() {
    Stopwatch sw = Stopwatch.start("отчет по периоду");
    logger.info("=== АНАЛИТИКА ПО ПЕРИОДУ ===");
    logger.info("Введите дату начала (формат: гггг-мм-дд или гггг-мм-дд чч:мм):");
    String startInput = scanner.nextLine().trim();
    if (startInput.isEmpty()) {
      logger.info("Отмена операции: дата начала не указана");
      System.out.printf(END_MSG);
      return;
    }
    logger.info("Введите дату окончания (формат: гггг-мм-дд или гггг-мм-дд чч:мм):");
    String endInput = scanner.nextLine().trim();
    if (endInput.isEmpty()) {
      logger.info("Отмена операции: дата окончания не указана");
      System.out.printf(END_MSG);
      return;
    }
    try {
      LocalDateTime start = parseDateTime(startInput);
      LocalDateTime end = parseDateTime(endInput);
      end = adjustEndDate(end, endInput);

      if (start.isAfter(end)) {
        logger.info(String.format(
            "Ошибка: дата начала (%s) позже даты окончания (%s)",
            start, end
        ));
        System.out.printf(END_MSG);
        return;
      }

      MoneySum result = bank.calculateDifferenceBetweenThreads(start, end);
      PeriodReportDisplay.PrintPeriodReport(start, end, result);
      System.out.printf(END_MSG);
      sw.stop();

    } catch (IllegalArgumentException e) {
      logger.info(e.getMessage());
    }
  }

  public void showGroups() {
    Stopwatch sw = Stopwatch.start("отчет по группам");
    Summary result = bank.calculateMoneyGroup();
    MoneyGroupDisplay.PrintMoneyGroup(result);
    System.out.printf(END_MSG);
    sw.stop();
  }
}
