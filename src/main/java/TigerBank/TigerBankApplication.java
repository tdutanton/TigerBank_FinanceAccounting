package TigerBank;

import TigerBank.Interaction.Interaction;
import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TigerBankApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(TigerBankApplication.class,
        args);

    Interaction interaction = context.getBean(Interaction.class);
    interaction.runMenu();
  }

  @Bean
  public Scanner scanner() {
    return new Scanner(System.in);
  }
}