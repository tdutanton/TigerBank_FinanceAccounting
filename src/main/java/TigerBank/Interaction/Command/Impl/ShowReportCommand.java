package TigerBank.Interaction.Command.Impl;

import TigerBank.Analytics.MoneySum;
import TigerBank.Interaction.Command.BaseCommand;
import TigerBank.Interaction.Command.CommandContext;
import TigerBank.Presentation.PeriodReportDisplay;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ShowReportCommand extends BaseCommand {

  public ShowReportCommand(CommandContext ctx) {
    super(ctx);
  }

  @Override
  public void execute() {
    withTiming("отчет по периоду", () -> {
      ctx.logger().info("=== АНАЛИТИКА ПО ПЕРИОДУ ===");

      String startInput = ctx.getStringInput(
          "Введите дату начала (формат: гггг-мм-дд или гггг-мм-дд чч:мм):");
      if (startInput.isEmpty()) {
        ctx.logger().info("Отмена операции: дата начала не указана");
        return;
      }

      String endInput = ctx.getStringInput(
          "Введите дату окончания (формат: гггг-мм-дд или гггг-мм-дд чч:мм):");
      if (endInput.isEmpty()) {
        ctx.logger().info("Отмена операции: дата окончания не указана");
        return;
      }

      try {
        LocalDateTime start = parseDateTime(startInput);
        LocalDateTime end = parseDateTime(endInput);
        end = adjustEndDate(end, endInput);

        if (start.isAfter(end)) {
          ctx.logger().info(String.format(
              "Ошибка: дата начала (%s) позже даты окончания (%s)", start, end));
          return;
        }

        MoneySum result = ctx.bank().calculateDifferenceBetweenThreads(start, end);
        PeriodReportDisplay.PrintPeriodReport(start, end, result);

      } catch (IllegalArgumentException e) {
        ctx.logger().info(e.getMessage());
      }
    });
  }

  private LocalDateTime parseDateTime(String input) {
    input = input.trim();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    try {
      return LocalDateTime.parse(input, dateTimeFormatter);
    } catch (DateTimeParseException e) {
      try {
        LocalDate date = LocalDate.parse(input, dateFormatter);
        return date.atStartOfDay();
      } catch (DateTimeParseException ex) {
        throw new IllegalArgumentException(
            "Неверный формат даты. Используйте:\n" +
                "  • 'гггг-мм-дд' (например: 2026-02-14)\n" +
                "  • 'гггг-мм-дд чч:мм' (например: 2026-02-14 15:30)");
      }
    }
  }

  private LocalDateTime adjustEndDate(LocalDateTime end, String rawInput) {
    if (rawInput.trim().length() == 10) {
      return end.toLocalDate().atTime(23, 59, 59, 999_999_999);
    }
    return end;
  }
}