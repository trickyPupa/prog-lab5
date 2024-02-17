package technical.commands.abstractions;


/**
 * Родительский интерфейс для всех команд.
 */
public interface Command {
    public void execute(String[] s);
}
