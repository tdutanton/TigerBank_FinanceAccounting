package Domain.Operation;

import Domain.TxType.TxType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Operation {
    private final Integer id;
    private final TxType type;
    private final Integer bankAccountId;
    private long amount;
    private final LocalDateTime date;
    private final Integer categoryId;
    private String description;

    public Operation(Integer id, TxType type, Integer bankAccountId, long amount, LocalDateTime date, Integer categoryId) {
        this.id = id;
        this.type = type;
        this.bankAccountId = bankAccountId;
        this.amount = amount;
        this.date = date;
        this.categoryId = categoryId;
        this.description = null;
    }
}
