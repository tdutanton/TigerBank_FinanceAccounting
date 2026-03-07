package TigerBank.ImportExport;

import java.io.IOException;
import java.nio.file.Path;

public interface DataFormat {

  void export(BankDataDTO data, Path filePath) throws IOException;

  BankDataDTO importData(Path filePath) throws IOException;

  String getFileExtension();
}