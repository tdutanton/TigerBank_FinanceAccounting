package TigerBank;

import TigerBank.Domain.TxType.TxType;
import TigerBank.Service.GeneralService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
// Уберите @ComponentScan - он не нужен, если все в подпакетах TigerBank
public class TigerBankApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(TigerBankApplication.class, args);

    GeneralService generalService = context.getBean(GeneralService.class);

    generalService.createAndSaveBankAccount("New", 1000);
    generalService.createAndSaveCategory(TxType.INCOME, "Зарплата");
  }
}