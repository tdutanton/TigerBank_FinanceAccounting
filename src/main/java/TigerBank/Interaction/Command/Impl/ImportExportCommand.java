package TigerBank.Interaction.Command.Impl;

import TigerBank.ImportExport.ImportExportService;
import TigerBank.Interaction.Command.BaseCommand;
import TigerBank.Interaction.Command.CommandContext;
import java.io.IOException;

public class ImportExportCommand extends BaseCommand {

  private final ImportExportService importExportService;

  public ImportExportCommand(CommandContext ctx, ImportExportService importExportService) {
    super(ctx);
    this.importExportService = importExportService;
  }

  @Override
  public void execute() {
    boolean back = false;
    while (!back) {
      ctx.logger().info("\n--- ИМПОРТ/ЭКСПОРТ ДАННЫХ ---");
      ctx.logger().info("1. Экспорт в JSON");
      ctx.logger().info("2. Экспорт в YAML");
      ctx.logger().info("3. Экспорт в CSV");
      ctx.logger().info("4. Импорт из файла");
      ctx.logger().info("0. Назад");
      ctx.logger().info("> ");

      int choice = ctx.getIntInput("");
      try {
        switch (choice) {
          case 1 -> exportData("json");
          case 2 -> exportData("yaml");
          case 3 -> exportData("csv");
          case 4 -> importData();
          case 0 -> back = true;
          default -> ctx.logger().info("Неверный выбор");
        }
      } catch (Exception e) {
        ctx.logger().info("Ошибка: " + e.getMessage());
      }
    }
  }

  private void exportData(String format) {
    withTiming("экспорт в файл", () -> {
      String filename = ctx.getStringInput("Введите имя файла (без расширения): ");
      if (filename.isEmpty()) {
        ctx.logger().info("Имя файла не может быть пустым");
        return;
      }
      try {
        importExportService.exportAll(format, filename);
      } catch (IOException e) {
        ctx.logger().info("Ошибка при экспорте: " + e.getMessage());
      }
    });
  }

  private void importData() {
    withTiming("импорт из файла", () -> {
      String filepath = ctx.getStringInput("Введите путь к файлу (например: exports/data.json): ");
      if (filepath.isEmpty()) {
        ctx.logger().info("Путь к файлу не может быть пустым");
        return;
      }

      if (!confirm(
          "Внимание! Импорт перезапишет все существующие данные. Продолжить? (да/нет): ")) {
        ctx.logger().info("Импорт отменён");
        return;
      }

      try {
        importExportService.importAll(filepath);
      } catch (IOException e) {
        ctx.logger().info("Ошибка при импорте: " + e.getMessage());
      }
    });
  }
}
