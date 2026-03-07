package TigerBank.Config;

import TigerBank.Utils.IDGenerator.IDGenerator;
import TigerBank.Utils.IDGenerator.SequentialIDGenerator;
import TigerBank.Utils.Logging.ConsoleLogger;
import TigerBank.Utils.Logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public Logger logger() {
    Logger logger = new ConsoleLogger();
    logger.setLogMode(true);
    return logger;
  }

  @Bean
  public IDGenerator accountIdGenerator() {
    return new SequentialIDGenerator("acc_");
  }

  @Bean
  public IDGenerator operationIdGenerator() {
    return new SequentialIDGenerator("op_");
  }

  @Bean
  public IDGenerator categoryIdGenerator() {
    return new SequentialIDGenerator("cat_");
  }
}