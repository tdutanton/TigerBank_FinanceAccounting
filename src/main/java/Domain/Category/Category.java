package Domain.Category;

import Domain.Identifiable.Identifiable;
import Domain.TxType.TxType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString(includeFieldNames = true)
public class Category implements Identifiable {

  @Getter
  private final String id;
  private final TxType type;
  @Getter
  private final String name;

  public Category(String id, TxType type, String name) {
    if (id == null || id.isEmpty()) {
      throw new IllegalArgumentException("ID не может быть null или пустым");
    }
    if (type == null) {
      throw new IllegalArgumentException("Тип не может быть null или пустым");
    }
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Имя не может быть null или пустым");
    }
    this.id = id;
    this.type = type;
    this.name = name;
  }
}
