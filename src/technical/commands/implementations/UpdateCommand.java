package technical.commands.implementations;

import necessary.Movie;
import technical.commands.abstractions.AbstractCommand;
import technical.managers.abstractions.AbstractCommandHandler;
import technical.managers.abstractions.AbstractReceiver;

import static technical.Utils.isInt;

import java.util.Arrays;
import java.util.Vector;

public class UpdateCommand extends AbstractCommand {
    public UpdateCommand() {
        super("update", "Команда для обновления значений элемента коллекции с заданным id.",
                "id {element}");
    }

    @Override
    public void execute(String[] s, AbstractReceiver rec) {
        rec.update(s);
    }
}
