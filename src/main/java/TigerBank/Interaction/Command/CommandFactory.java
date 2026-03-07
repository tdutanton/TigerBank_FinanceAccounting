package TigerBank.Interaction.Command;

@FunctionalInterface
public interface CommandFactory {

  Command create(CommandContext ctx);
}
