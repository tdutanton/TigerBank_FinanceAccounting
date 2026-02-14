package TigerBank.Domain.Account;

/**
 * Интерфейс для сущностей, способных получать деньги
 */
public interface Depositable {

  void deposit(long amount);
}
