package Domain.Account;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class BankAccount {
    @Getter
    private final String id;
    private String name;
    private long balance;
}
