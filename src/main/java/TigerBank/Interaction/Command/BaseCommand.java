package TigerBank.Interaction.Command;

import TigerBank.Utils.Stopwatch.Stopwatch;

public abstract class BaseCommand implements Command {

  private static final String END_MSG = "< Конец сообщения >%n%n";
  protected final CommandContext ctx;

  protected BaseCommand(CommandContext ctx) {
    this.ctx = ctx;
  }

  protected void withTiming(String operationName, Runnable action) {
    Stopwatch sw = Stopwatch.start(operationName);
    try {
      action.run();
    } finally {
      System.out.printf(END_MSG);
      sw.stop();
    }
  }

  protected boolean confirm(String message) {
    ctx.logger().info(message);
    String confirm = ctx.scanner().nextLine().trim().toLowerCase();
    return confirm.equals("да") || confirm.equals("yes") || confirm.equals("y");
  }
}