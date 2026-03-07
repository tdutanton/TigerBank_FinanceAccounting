package TigerBank.ImportExport;

import TigerBank.Domain.TxType.TxType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class OperationDTO {

  private String id;
  private TxType type;
  private String bankAccountId;
  private long amount;
  private LocalDateTime date;
  private String categoryId;
  private String description;
}
