package TigerBank.Interaction.Command;

public interface Command {

  void execute();

  default String getName() {
    return getClass().getSimpleName();
  }
}