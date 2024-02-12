package technical.commands.implementations;

import technical.commands.abstractions.AbstractCommand;
import technical.managers.AbstractCommandHandler;

public class ExitCommand extends AbstractCommand {

    public ExitCommand(AbstractCommandHandler.ShellValuables shell) {
        super("exit", "Команда для завершения работы.", shell);
    }

    @Override
    public void execute(String[] s) {
        System.exit(0);
    }
}
