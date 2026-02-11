package Domain.Account;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class BankAccount {
    private final Integer id;
    private String name;
    private long balance;
}
