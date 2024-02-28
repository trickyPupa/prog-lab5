package technical.commands.implementations;

import technical.commands.abstractions.AbstractCommand;
import technical.managers.abstractions.AbstractCommandHandler;
import technical.managers.abstractions.AbstractReceiver;

public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit", "Команда для завершения работы.", "no");
    }

    @Override
    public void execute(String[] s, AbstractReceiver rec) {
        rec.exit(s);
    }
}
