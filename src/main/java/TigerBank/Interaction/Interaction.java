package TigerBank.Interaction;

import TigerBank.Domain.TxType.TxType;
import TigerBank.Presentation.TxTypeDisplay;
import TigerBank.Service.GeneralService;
import TigerBank.Utils.Logging.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.springframework.stereotype.Service;

@Service
public class Interaction {

  private final Logger logger;
  private final GeneralService bank;
  private final Scanner scanner;
  private final Map<Integer, Runnable> menuActions;

  private static final String END_MSG = "< Конец сообщения >%n%n";

  public Interaction(Logger logger, GeneralService bank, Scanner scanner) {
    this.logger = logger;
    this.bank = bank;
    this.scanner = scanner;
    this.menuActions = createMenuActions();
  }

  private Map<Integer, Runnable> createMenuActions() {
    Map<Integer, Runnable> map = new HashMap<>();
    map.put(1, this::createCustomer);
    map.put(2, this::createCategory);
    map.put(3, this::deposit);
//    map.put(3, this::withdraw);
//    map.put(4, this::transfer);
//    map.put(5, this::showCustomerAccounts);
//    map.put(6, this::showTransactions);
//    map.put(7, this::showBankReport);
    return map;
  }

  public void showMenu() {
    System.out.println("--- МЕНЮ ---");
    System.out.println("Выберите действие: ");
    System.out.println("1.	Создать Счет");
    System.out.println("2.	Создать категорию");
    System.out.println("3.	Пополнить");
    System.out.println("4.	Перевести");
    System.out.println("5.	Показать счета клиента");
    System.out.println("6.	Показать транзакции");
    System.out.println("7.	Отчёт банка");
    System.out.println("0.	Выход");
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

  public int getIntInput() {
    while (!scanner.hasNextInt()) {
      logger.info("Пожалуйста, введите число: ");
      scanner.next();
    }
    return scanner.nextInt();
  }

  private double getDoubleInput() {
    while (!scanner.hasNextDouble()) {
      logger.info("Пожалуйста, введите корректную сумму: ");
      scanner.next();
    }
    return scanner.nextDouble();
  }

  private long getLongInput() {
    while (!scanner.hasNextLong()) {
      logger.info("Пожалуйста, введите корректную сумму: ");
      scanner.next();
    }
    return scanner.nextLong();
  }

  public void createCustomer() {
    logger.info("Введите имя счета: ");
    scanner.nextLine();
    String name = scanner.nextLine();
    try {
      bank.createAndSaveBankAccount(name);
      logger.info("Создан счет: " + name);
      System.out.printf(END_MSG);
    } catch (IllegalArgumentException e) {
      logger.info(e.toString());
    }
  }

  public void createCategory() {
    logger.info("Введите тип категории (доход, расход): ");
    scanner.nextLine();
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
  }

  public void deposit() {
    logger.info("Введите номер счёта: ");
    scanner.nextLine();
    String name = scanner.nextLine().trim();
    if (name.isEmpty()) {
      logger.info("Имя счёта не может быть пустым");
      System.out.printf(END_MSG);
      return;
    }
    logger.info("Введите сумму: ");
    long amount = getLongInput();
    scanner.nextLine();
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
      logger.info(String.format("Счёт '%s' пополнен на %d с категорией '%s'%s",
          name, amount, category,
          description != null ? " и описанием: " + description : ""));
    } catch (IllegalArgumentException e) {
      logger.info("Ошибка: " + e.getMessage());
    }
    System.out.printf(END_MSG);
  }

/*
  public void withdraw() {
    System.out.print("Введите номер счёта: ");
    int accountNumber = getIntInput();
    Account account = bank.findAccount(accountNumber);
    if (account != null) {
      System.out.print("Введите сумму снятия: ");
      double amount = getDoubleInput();
      if (bank.withdraw(accountNumber, amount)) {
        System.out.printf("Со счета %d снято %.2f руб.%n", accountNumber, amount);
      } else {
        System.out.printf("Снятие не удалось.%n");
      }
    } else {
      System.out.printf("Клиент с номером счета %d не найдет.%n", accountNumber);
    }
    System.out.printf(END_MSG);
  }

  public void transfer() {
    System.out.print("Введите номер счёта отправителя: ");
    int accountNumberFrom = getIntInput();
    Account accountFrom = bank.findAccount(accountNumberFrom);
    System.out.print("Введите номер счёта получателя: ");
    int accountNumberTo = getIntInput();
    Account accountTo = bank.findAccount(accountNumberTo);
    if (accountFrom != null && accountTo != null) {
      System.out.print("Введите сумму перевода: ");
      double amount = getDoubleInput();
      if (bank.transfer(accountNumberFrom, accountNumberTo, amount)) {
        System.out.printf("Со счета %d на счет %d переведено %.2f руб.%n", accountNumberFrom,
            accountNumberTo, amount);
      } else {
        System.out.printf("Перевод не удался.%n");
      }
    } else {
      System.out.printf("Клиент(-ы) не найден(-ы).%n");
    }
    System.out.printf(END_MSG);
  }

  public void showCustomerAccounts() {
    System.out.print("Введите полное имя клиента: ");
    scanner.nextLine();
    String name = scanner.nextLine();
    Customer customer = bank.findCustomer(name);
    if (customer != null) {
      bank.printCustomerAccounts(customer.getId());
    } else {
      System.out.printf("Клиент %s не найдет.%n", name);
    }
    System.out.printf(END_MSG);
  }

  public void showTransactions() {
    System.out.println("Отчет по транзакциям");
    bank.printTransactions();
    System.out.printf(END_MSG);
  }

  public void showBankReport() {
    System.out.println("Отчет банка сформирован");
    bank.printReport();
    System.out.printf(END_MSG);
  }*/
}
