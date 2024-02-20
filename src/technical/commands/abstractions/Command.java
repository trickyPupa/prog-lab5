package technical.commands.abstractions;


/**
 * Родительский интерфейс для всех команд.
 */
public interface Command {
    String getName();

    /**
     * Исполнение логики команды.
     * @param s - аргументы, передаваемые команде.
     */
    void execute(String[] s);
}
