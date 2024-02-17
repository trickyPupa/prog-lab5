package technical.commands.implementations;

import technical.commands.abstractions.AbstractCommand;
import technical.managers.abstractions.AbstractCommandHandler;

public class ShowCommand extends AbstractCommand {
    public ShowCommand(AbstractCommandHandler.ShellValuables shell) {
        super("show", "Команда для просмотра коллекции.", "no", shell);
    }

    @Override
    public void execute(String[] s) {
        shell.getOutputManager().print(shell.getCollectionManager().presentView());
    }
}
