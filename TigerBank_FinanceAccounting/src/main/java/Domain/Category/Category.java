package Domain.Category;

import Domain.TxType.TxType;
import Domain.TxType.TxTypeDisplay;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Category {
    private final Integer id;
    private final TxType type;
    private final String name;

    public String getTypeDisplayName() {
        return TxTypeDisplay.getDisplayName(type);
    }
}
