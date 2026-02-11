package Repository;

import Domain.Account.BankAccount;

import java.util.HashMap;
import java.util.Map;

/**
 * String - id счета
 */
public class AccountRepository {
    private final Map<String, BankAccount> accounts;

    public AccountRepository() {
        accounts = new HashMap<>();
    }
}
