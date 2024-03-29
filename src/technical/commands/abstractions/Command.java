package technical.commands.abstractions;


import technical.managers.CommandReceiver;
import technical.managers.abstractions.AbstractReceiver;

/**
 * Родительский интерфейс для всех команд.
 */
public interface Command {
    String getName();

    /**
     * Исполнение логики команды.
     * @param s - аргументы, передаваемые команде.
     */
    void execute(String[] s, AbstractReceiver rec);
}
