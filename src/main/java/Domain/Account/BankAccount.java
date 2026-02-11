package Domain.Account;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString(includeFieldNames=true)
public class BankAccount {
    @Getter
    private final String id;
    private String name;
    private long balance;

    public BankAccount(String id, String name, long balance) {
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
}
