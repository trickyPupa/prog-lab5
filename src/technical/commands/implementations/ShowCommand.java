package technical.commands.implementations;

import technical.commands.abstractions.AbstractCommand;
import technical.managers.abstractions.AbstractCommandHandler;
import technical.managers.abstractions.AbstractReceiver;

public class ShowCommand extends AbstractCommand {
    public ShowCommand() {
        super("show", "Команда для просмотра коллекции.", "no");
    }

    @Override
    public void execute(String[] s, AbstractReceiver rec) {
        rec.show(s);
    }
}
