package technical.commands.implementations;

import necessary.Movie;
import technical.commands.abstractions.AbstractCommand;
import technical.managers.CollectionManager;
import technical.managers.abstractions.AbstractCommandHandler;
import technical.managers.abstractions.AbstractReceiver;

import java.util.Vector;

public class RemoveLowerCommand extends AbstractCommand {
    public RemoveLowerCommand() {
        super("remove_lower", "Команда для удаления всех элементов коллекции, меньших чем заданный.",
                "{element}");
    }

    @Override
    public void execute(String[] s, AbstractReceiver rec) {
        rec.removeLower(s);
    }
}
