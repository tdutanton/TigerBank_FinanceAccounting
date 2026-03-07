package TigerBank.ImportExport;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDataDTO {

  private List<AccountDTO> accounts;
  private List<CategoryDTO> categories;
  private List<OperationDTO> operations;
}