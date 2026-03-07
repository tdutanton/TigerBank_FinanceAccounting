package TigerBank.Interaction.Command.Impl;

import TigerBank.Interaction.Command.BaseCommand;
import TigerBank.Interaction.Command.CommandContext;

public class CreateCustomerCommand extends BaseCommand {

  public CreateCustomerCommand(CommandContext ctx) {
    super(ctx);
  }

  @Override
  public void execute() {
    withTiming("создание счёта", () -> {
      String name = ctx.getStringInput("Введите имя счета: ");
      if (name.isEmpty()) {
        ctx.logger().info("Имя счёта не может быть пустым");
        return;
      }
      try {
        ctx.bank().createAndSaveBankAccount(name);
        ctx.logger().info("Создан счет: " + name);
      } catch (IllegalArgumentException e) {
        ctx.logger().info(e.toString());
      }
    });
  }
}
