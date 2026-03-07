package TigerBank.Domain.Account;

/**
 * Интерфейс для сущностей, способных списывать деньги
 */
public interface Withdrawable {

  void withdraw(long amount);

  long getBalance();
}
