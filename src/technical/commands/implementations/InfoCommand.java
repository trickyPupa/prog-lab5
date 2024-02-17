package technical.commands.implementations;

import technical.commands.abstractions.AbstractCommand;
import technical.managers.abstractions.AbstractCommandHandler;
import technical.managers.abstractions.IOutputManager;

import java.util.Map;

public class InfoCommand extends AbstractCommand {
    public InfoCommand(AbstractCommandHandler.ShellValuables shell){
        super("info", "Команда для вывода информации о коллекции.", "no", shell);
    }

    @Override
    public void execute(String[] s) {
        IOutputManager output = shell.getOutputManager();
        Map<String, String> info = shell.getCollectionManager().getInfo();
        output.print("Информация о коллекции:");

        for(String key : info.keySet()){
            output.print("\t" + key + " - " + info.get(key));
        }
    }
}
