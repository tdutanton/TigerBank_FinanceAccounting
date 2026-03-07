package TigerBank.Presentation;

import TigerBank.Domain.Account.Account;
import java.util.ArrayList;

public class AccountsDisplay {

  private AccountsDisplay() {
  }

  public static void PrintAccounts(ArrayList<Account> accounts) {
    if (accounts.isEmpty()) {
      System.out.println("Счета отсутствуют");
      return;
    }
    for (Account account : accounts) {
      System.out.println(account);
    }
  }

}
