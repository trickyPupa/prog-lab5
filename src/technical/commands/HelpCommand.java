package technical.commands;

import technical.Command;
import technical.CommandHandler;

import java.util.HashMap;

public class HelpCommand extends Command {
    private HashMap<String, Command> commandsList = CommandHandler.commands;
    public HelpCommand(){
        super("help", "Команда для вывода справки по всем доступным командам.");
    }

    @Override
    public boolean execute(String[] s) {
        System.out.println("Список доступных команд.");
        for (String name : commandsList.keySet()){
            System.out.printf("\t%s: \t%s}\n", name, commandsList.get(name).getDescription());
        }
        return true;
    }
}
