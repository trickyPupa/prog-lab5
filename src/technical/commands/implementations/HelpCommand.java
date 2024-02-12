package technical.commands.implementations;

import technical.commands.abstractions.AbstractCommand;
import technical.managers.AbstractCommandHandler;
import technical.managers.CollectionManager;
import technical.managers.CommandHandler;

import java.util.HashMap;

public class HelpCommand extends AbstractCommand {
    private final HashMap<String, AbstractCommand> commandsList = shell.commands;
    public HelpCommand(AbstractCommandHandler.ShellValuables shell){
        super("help", "Команда для вывода справки по всем доступным командам.", shell);
    }

    @Override
    public void execute(String[] s) {
        System.out.println("Список доступных команд.");
        for (String name : commandsList.keySet()){
            System.out.printf("\t%s: \t%s}\n", name, commandsList.get(name).getDescription());
        }
    }
}
