package TigerBank.Interaction.Command.Impl;

import TigerBank.Domain.Operation.Operation;
import TigerBank.Interaction.Command.BaseCommand;
import TigerBank.Interaction.Command.CommandContext;
import TigerBank.Presentation.OperationsDisplay;
import java.util.ArrayList;

public class ShowOperationsCommand extends BaseCommand {

  public ShowOperationsCommand(CommandContext ctx) {
    super(ctx);
  }

  @Override
  public void execute() {
    withTiming("показать операции", () -> {
      ctx.logger().info("Операции: ");
      ArrayList<Operation> operations = ctx.bank().operations();
      OperationsDisplay.PrintOperations(operations);
    });
  }
}