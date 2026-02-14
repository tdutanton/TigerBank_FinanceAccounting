package TigerBank.ImportExport;

import TigerBank.Domain.TxType.TxType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class CategoryDTO {

  private String id;
  private TxType type;
  private String name;
}
