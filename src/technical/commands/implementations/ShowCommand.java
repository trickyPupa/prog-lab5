package technical.commands.implementations;

import technical.commands.abstractions.AbstractCommand;
import technical.managers.AbstractCommandHandler;

public class ShowCommand extends AbstractCommand {
    public ShowCommand(AbstractCommandHandler.ShellValuables shell) {
        super("show", "Команда для просмотра коллекции.", shell);
    }

    @Override
    public void execute(String[] s) {
        shell.getOutputManager().print(shell.getCollectionManager().presentView());
    }
}
