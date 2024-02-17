package technical.commands.implementations;

import technical.commands.abstractions.AbstractCommand;
import technical.managers.abstractions.AbstractCommandHandler;

public class ClearCommand extends AbstractCommand {
    public ClearCommand(AbstractCommandHandler.ShellValuables shell){
        super("clear", "Команда для очищения коллекции.", "no", shell);
    }

    @Override
    public void execute(String[] s) {
        shell.getCollectionManager().clear();
        shell.getOutputManager().print("Коллекция очищена.");
    }
}
