package TigerBank.Interaction.Command.Impl;

import TigerBank.Interaction.Command.BaseCommand;
import TigerBank.Interaction.Command.CommandContext;

public class WithdrawCommand extends BaseCommand {

  public WithdrawCommand(CommandContext ctx) {
    super(ctx);
  }


  @Override
  public void execute() {
    withTiming("снятие", () -> {
      String accountName = ctx.getStringInput("Введите номер счёта: ");
      if (accountName.isEmpty()) {
        ctx.logger().info("Имя счёта не может быть пустым");
        return;
      }
      long amount = ctx.getLongInput("Введите сумму: ");

      String category = ctx.getStringInput("Введите категорию: ");
      if (category.isEmpty()) {
        ctx.logger().info("Категория не может быть пустой");
        return;
      }

      String description = ctx.getStringInput("Введите описание (опционально): ");
      if (description.isEmpty()) {
        description = null;
      }

      try {
        if (description != null) {
          ctx.bank().withdrawFromAccount(accountName, amount, category, description);
        } else {
          ctx.bank().withdrawFromAccount(accountName, amount, category);
        }
        ctx.logger().info(String.format("Со счета '%s' снято на %.2f с категорией '%s'%s",
            accountName, amount / 100.0, category,
            description != null ? " и описанием: " + description : ""));
      } catch (IllegalArgumentException e) {
        ctx.logger().info("Ошибка: " + e.getMessage());
      }
    });
  }
}
