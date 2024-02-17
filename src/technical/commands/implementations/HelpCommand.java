package technical.commands.implementations;

import technical.commands.abstractions.AbstractCommand;
import technical.managers.abstractions.AbstractCommandHandler;
import technical.managers.abstractions.IOutputManager;

import java.util.HashMap;

public class HelpCommand extends AbstractCommand {
    private final HashMap<String, AbstractCommand> commandsList = shell.commands;
    public HelpCommand(AbstractCommandHandler.ShellValuables shell){
        super("help", "Команда для вывода справки по всем доступным командам.", "no", shell);
    }

    @Override
    public void execute(String[] s) {
        /*if (!s[0].isBlank() && commandsList.containsKey(s[0].strip())){
            System.out.println("Справка по команде: " + s[0].strip());
            System.out.printf("\t%s: \t%s\n", s[0].strip(), commandsList.get(s[0].strip()).getDescription());
        }
        else {
            System.out.println("Список доступных команд.");
            for (String name : commandsList.keySet()) {
                System.out.printf("\t%s: \t%s\n", name, commandsList.get(name).getDescription());
            }
        }*/
        IOutputManager output = shell.getOutputManager();

        output.print("Список доступных команд.");
        for (String name : commandsList.keySet()) {
            String temp = name;
            if (!commandsList.get(name).getArguments().equals("no")) temp += " " + commandsList.get(name).getArguments();
            output.print(String.format("\t%s: \t%s", temp, commandsList.get(name).getDescription()));
        }
    }
}
