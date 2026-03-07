package TigerBank.Interaction.Command.Impl;

import TigerBank.Domain.Category.Category;
import TigerBank.Interaction.Command.BaseCommand;
import TigerBank.Interaction.Command.CommandContext;
import TigerBank.Presentation.CategoriesDisplay;
import java.util.ArrayList;

public class ShowCategoriesCommand extends BaseCommand {

  public ShowCategoriesCommand(CommandContext ctx) {
    super(ctx);
  }

  @Override
  public void execute() {
    withTiming("показать категории", () -> {
      ctx.logger().info("Категории: ");
      ArrayList<Category> categories = ctx.bank().categories();
      CategoriesDisplay.PrintCategories(categories);
    });
  }
}