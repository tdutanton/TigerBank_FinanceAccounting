package Domain.Operation;

import Domain.TxType.TxType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@EqualsAndHashCode
@ToString(includeFieldNames = true)
public class Operation {
    private final String id;
    private final TxType type;
    private final String bankAccountId;
    private long amount;
    private final LocalDateTime date;
    private final String categoryId;
    private String description;

    public Operation(String id, TxType type, String bankAccountId, long amount, LocalDateTime date, String categoryId, String description) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID Operation не может быть null или пустым");
        }
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Тип не может быть null или пустым");
        }
        if (bankAccountId == null || bankAccountId.isEmpty()) {
            throw new IllegalArgumentException("ID bankAccountId не может быть null или пустым");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма не может быть отрицательной");
        }
        if (date == null) {
            throw new IllegalArgumentException("Дата должна быть заполнена");
        }
        if (categoryId == null || categoryId.trim().isEmpty()) {
            throw new IllegalArgumentException("ID categoryId не может быть null или пустым");
        }

        this.id = id;
        this.type = type;
        this.bankAccountId = bankAccountId;
        this.amount = amount;
        this.date = date;
        this.categoryId = categoryId;
        if (description == null || description.isEmpty()) {
            this.description = null;
        } else {
            this.description = description;
        }
    }

    public Operation(String id, TxType type, String bankAccountId, long amount, LocalDateTime date, String categoryId) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID Operation не может быть null или пустым");
        }
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Тип не может быть null или пустым");
        }
        if (bankAccountId == null || bankAccountId.isEmpty()) {
            throw new IllegalArgumentException("ID bankAccountId не может быть null или пустым");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма не может быть отрицательной");
        }
        if (date == null) {
            throw new IllegalArgumentException("Дата должна быть заполнена");
        }
        if (categoryId == null || categoryId.trim().isEmpty()) {
            throw new IllegalArgumentException("ID categoryId не может быть null или пустым");
        }

        this.id = id;
        this.type = type;
        this.bankAccountId = bankAccountId;
        this.amount = amount;
        this.date = date;
        this.categoryId = categoryId;
        this.description = null;
    }
}
