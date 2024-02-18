package technical.commands.abstractions;


/**
 * Родительский интерфейс для всех команд.
 */
public interface Command {
    String getName();
    void execute(String[] s);
}
