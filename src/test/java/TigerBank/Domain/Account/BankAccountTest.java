package TigerBank.Domain.Account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BankAccountTest {

  @Test
  void shouldCreateAccountWithInitialBalance() {
    BankAccount account = new BankAccount("acc_1", "Основной", 5000);

    assertEquals("acc_1", account.getId());
    assertEquals("Основной", account.getName());
    assertEquals(5000, account.getBalance());
  }

  @Test
  void shouldCreateAccountWithZeroBalance() {
    BankAccount account = new BankAccount("acc_2", "Сберегательный");

    assertEquals(0, account.getBalance());
  }

  @Test
  void shouldIncreaseBalanceOnDeposit() {
    BankAccount account = new BankAccount("acc_1", "Счёт", 1000);
    account.deposit(500);
    assertEquals(1500, account.getBalance());
  }

  @Test
  void shouldDecreaseBalanceOnWithdraw() {
    BankAccount account = new BankAccount("acc_1", "Счёт", 1000);
    account.withdraw(300);
    assertEquals(700, account.getBalance());
  }

  @Test
  void shouldAllowOverdraft() {
    BankAccount account = new BankAccount("acc_1", "Счёт", 100);
    account.withdraw(200); // разрешаем уход в минус
    assertEquals(-100, account.getBalance());
  }

  @Test
  void shouldHaveCorrectToString() {
    BankAccount account = new BankAccount("acc_1", "Основной", 5000);
    String str = account.toString();
    assertTrue(str.contains("id=acc_1"));
    assertTrue(str.contains("name=Основной"));
    assertTrue(str.contains("balance=5000"));
  }
}