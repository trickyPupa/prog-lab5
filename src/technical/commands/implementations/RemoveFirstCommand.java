package technical.commands.implementations;

import technical.commands.abstractions.AbstractCommand;
import technical.managers.abstractions.AbstractCommandHandler;
import technical.managers.abstractions.AbstractReceiver;

public class RemoveFirstCommand extends AbstractCommand {
    public RemoveFirstCommand() {
        super("remove_first", "Команда для удаления первого элемента коллекции.",
                "no");
    }

    @Override
    public void execute(String[] s, AbstractReceiver rec) {
        rec.removeFirst(s);
    }
}
