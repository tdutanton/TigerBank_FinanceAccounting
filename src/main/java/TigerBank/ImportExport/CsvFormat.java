package TigerBank.ImportExport;

import TigerBank.Domain.TxType.TxType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CsvFormat implements DataFormat {

  private static final String DELIMITER = ",";
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

  @Override
  public void export(BankDataDTO data, Path filePath) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
      writer.write("# ACCOUNTS");
      writer.newLine();
      writer.write("id,name,balance");
      writer.newLine();
      for (AccountDTO account : data.getAccounts()) {
        writer.write(String.join(DELIMITER,
            escape(account.getId()),
            escape(account.getName()),
            String.valueOf(account.getBalance())
        ));
        writer.newLine();
      }
      writer.newLine();

      writer.write("# CATEGORIES");
      writer.newLine();
      writer.write("id,type,name");
      writer.newLine();
      for (CategoryDTO category : data.getCategories()) {
        writer.write(String.join(DELIMITER,
            escape(category.getId()),
            category.getType().name(),
            escape(category.getName())
        ));
        writer.newLine();
      }
      writer.newLine();

      writer.write("# OPERATIONS");
      writer.newLine();
      writer.write("id,type,bankAccountId,amount,date,categoryId,description");
      writer.newLine();
      for (OperationDTO operation : data.getOperations()) {
        writer.write(String.join(DELIMITER,
            escape(operation.getId()),
            operation.getType().name(),
            escape(operation.getBankAccountId()),
            String.valueOf(operation.getAmount()),
            operation.getDate().format(DATE_FORMATTER),
            escape(operation.getCategoryId()),
            escape(operation.getDescription() != null ? operation.getDescription() : "")
        ));
        writer.newLine();
      }
    }
  }

  @Override
  public BankDataDTO importData(Path filePath) throws IOException {
    List<AccountDTO> accounts = new ArrayList<>();
    List<CategoryDTO> categories = new ArrayList<>();
    List<OperationDTO> operations = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
      String line;
      String currentSection = null;
      boolean isHeader = false;

      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty()) {
          continue;
        }

        if (line.startsWith("#")) {
          currentSection = line.substring(1).trim();
          isHeader = true;
          continue;
        }

        if (isHeader) {
          isHeader = false;
          continue;
        }

        String[] values = parseCsvLine(line);

        switch (currentSection) {
          case "ACCOUNTS" -> accounts.add(new AccountDTO(
              unescape(values[0]), unescape(values[1]), Long.parseLong(values[2])
          ));
          case "CATEGORIES" -> categories.add(new CategoryDTO(
              unescape(values[0]), TxType.valueOf(values[1]), unescape(values[2])
          ));
          case "OPERATIONS" -> operations.add(new OperationDTO(
              unescape(values[0]),
              TxType.valueOf(values[1]),
              unescape(values[2]),
              Long.parseLong(values[3]),
              LocalDateTime.parse(values[4], DATE_FORMATTER),
              unescape(values[5]),
              unescape(values[6])
          ));
        }
      }
    }

    return new BankDataDTO(accounts, categories, operations);
  }

  @Override
  public String getFileExtension() {
    return ".csv";
  }

  private String escape(String value) {
    if (value == null) {
      return "";
    }
    if (value.contains(DELIMITER) || value.contains("\"") || value.contains("\n")) {
      return "\"" + value.replace("\"", "\"\"") + "\"";
    }
    return value;
  }

  private String unescape(String value) {
    if (value == null) {
      return "";
    }
    if (value.startsWith("\"") && value.endsWith("\"")) {
      value = value.substring(1, value.length() - 1);
      value = value.replace("\"\"", "\"");
    }
    return value;
  }

  private String[] parseCsvLine(String line) {
    List<String> tokens = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    boolean inQuotes = false;

    for (char c : line.toCharArray()) {
      if (c == '"') {
        inQuotes = !inQuotes;
      } else if (c == ',' && !inQuotes) {
        tokens.add(sb.toString());
        sb.setLength(0);
      } else {
        sb.append(c);
      }
    }
    tokens.add(sb.toString());

    return tokens.toArray(new String[0]);
  }
}