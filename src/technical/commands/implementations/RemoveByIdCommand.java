package technical.commands.implementations;

import necessary.Movie;
import technical.commands.abstractions.AbstractCommand;
import technical.managers.abstractions.AbstractCommandHandler;
import technical.managers.abstractions.AbstractReceiver;

import java.util.Vector;

import static technical.Utils.isInt;

public class RemoveByIdCommand extends AbstractCommand {
    public RemoveByIdCommand() {
        super("remove_by_id", "Команда для удаления элемента коллекции с заданным id.",
                "id");
    }

    @Override
    public void execute(String[] s, AbstractReceiver rec) {
        rec.removeById(s);
    }
}
