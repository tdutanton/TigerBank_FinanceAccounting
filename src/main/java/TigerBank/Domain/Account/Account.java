package TigerBank.Domain.Account;

import TigerBank.Domain.Identifiable.Identifiable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString(includeFieldNames = true)
public abstract class Account implements Identifiable {

  @Getter
  protected final String id;
  protected String name;
  @Getter
  protected long balance;

  public Account(String id, String name, long balance) {
    if (id == null || id.isEmpty()) {
      throw new IllegalArgumentException("ID счета не может быть null или пустым");
    }
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Имя счета не может быть null или пустым");
    }
    if (balance < 0) {
      throw new IllegalArgumentException("Баланс не может быть отрицательным");
    }
    this.id = id;
    this.name = name;
    this.balance = balance;
  }

  public Account(String id, String name) {
    if (id == null || id.isEmpty()) {
      throw new IllegalArgumentException("ID счета не может быть null или пустым");
    }
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Имя счета не может быть null или пустым");
    }
    this.id = id;
    this.name = name;
    this.balance = 0;
  }
}
