package Domain.Category;

import Domain.TxType.TxType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Category {
    private final String id;
    private final TxType type;
    private final String name;
}
