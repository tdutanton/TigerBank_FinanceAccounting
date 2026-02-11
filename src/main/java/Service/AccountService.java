package Service;

import Domain.Account.Account;
import Domain.Account.BankAccount;
import Repository.AccountRepository;
import Utils.Logging.Logger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository repository;
    private final Logger logger;

    public void createAndSaveBankAccount(String id, String name, long balance) {
        try {
            BankAccount account = new BankAccount(id, name, balance);
            repository.addAccount(account);
        } catch (IllegalArgumentException e) {
            logger.info(e.toString());
        }
    }

    public void createAndSaveBankAccount(String id, String name) {
        try {
            BankAccount account = new BankAccount(id, name);
            repository.addAccount(account);
        } catch (IllegalArgumentException e) {
            logger.info(e.toString());
        }
    }

    public void deleteBankAccount(String id) {
        Account account = repository.accountById(id);
        if (account != null) {
            repository.deleteAccount(repository.accountById(id));
        }
    }
}
