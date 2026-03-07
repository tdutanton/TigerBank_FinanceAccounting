package TigerBank.Interaction.Command.Impl;

import TigerBank.Analytics.MoneyGroup.Summary;
import TigerBank.Interaction.Command.BaseCommand;
import TigerBank.Interaction.Command.CommandContext;
import TigerBank.Presentation.MoneyGroupDisplay;

public class ShowGroupsCommand extends BaseCommand {

  public ShowGroupsCommand(CommandContext ctx) {
    super(ctx);
  }

  @Override
  public void execute() {
    withTiming("отчет по группам", () -> {
      Summary result = ctx.bank().calculateMoneyGroup();
      MoneyGroupDisplay.PrintMoneyGroup(result);
    });
  }
}
