package TigerBank.Interaction.Command.Impl;

import TigerBank.Domain.TxType.TxType;
import TigerBank.Interaction.Command.BaseCommand;
import TigerBank.Interaction.Command.CommandContext;
import TigerBank.Presentation.TxTypeDisplay;

public class CreateCategoryCommand extends BaseCommand {

  public CreateCategoryCommand(CommandContext ctx) {
    super(ctx);
  }

  @Override
  public void execute() {
    withTiming("создание категории", () -> {
      String type = ctx.getStringInput("Введите тип категории (доход, расход): ").toLowerCase();
      if (type.isEmpty()) {
        ctx.logger().info("Тип не может быть пустым");
        return;
      }
      TxType txType;
      switch (type) {
        case "доход" -> txType = TxType.INCOME;
        case "расход" -> txType = TxType.EXPENSE;
        default -> {
          ctx.logger().info(
              "Неизвестный тип категории '" + type + "'. Допустимые значения: 'доход', 'расход'");
          return;
        }
      }
      String name = ctx.getStringInput("Введите имя категории: ");
      if (name.isEmpty()) {
        ctx.logger().info("Имя не может быть пустым");
        return;
      }
      ctx.bank().createAndSaveCategory(txType, name);
      ctx.logger().info(String.format("Категория '%s' (%s) создана",
          name, TxTypeDisplay.getDisplayName(txType)));
    });
  }
}