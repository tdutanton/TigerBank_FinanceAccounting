import Repository.AccountRepository;
import Service.AccountService;
import Utils.Logging.ConsoleLogger;
import Utils.Logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TigerBankApplication {
    public static void main(String[] args) {
        Logger logger = new ConsoleLogger();
        logger.setLogMode(true);

        AccountRepository repository = new AccountRepository(logger);
        AccountService service = new AccountService(repository, logger);

        service.createAndSaveBankAccount("asd", "new", 1000);
        service.depositById("asd", 5000);

    }
}
