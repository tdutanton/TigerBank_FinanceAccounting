package Domain.Account;

public interface Withdrawable {

  void withdraw(long amount);

  long getBalance();
}
