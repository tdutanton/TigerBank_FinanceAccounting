package TigerBank.ImportExport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class AccountDTO {

  private String id;
  private String name;
  private long balance;
}