package TigerBank.Interaction.Command.Impl;

import TigerBank.Domain.Account.Account;
import TigerBank.Interaction.Command.BaseCommand;
import TigerBank.Interaction.Command.CommandContext;
import TigerBank.Presentation.AccountsDisplay;
import java.util.ArrayList;

public class ShowAccountsCommand extends BaseCommand {

  public ShowAccountsCommand(CommandContext ctx) {
    super(ctx);
  }

  @Override
  public void execute() {
    withTiming("показать счета", () -> {
      ctx.logger().info("Текущие счета: ");
      ArrayList<Account> accounts = ctx.bank().accounts();
      AccountsDisplay.PrintAccounts(accounts);
    });
  }
}
