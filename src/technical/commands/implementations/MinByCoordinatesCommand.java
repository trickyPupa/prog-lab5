package technical.commands.implementations;

import necessary.Movie;
import technical.commands.abstractions.AbstractCommand;
import technical.managers.abstractions.AbstractCommandHandler;
import technical.managers.abstractions.AbstractReceiver;

import java.util.Objects;
import java.util.Vector;

public class MinByCoordinatesCommand extends AbstractCommand {
    public MinByCoordinatesCommand() {
        super("min_by_coordinates", "Команда для вывода элемента коллекции с минимальными координатами.",
                "no");
    }

    @Override
    public void execute(String[] s, AbstractReceiver rec) {
        rec.minByCoordinates(s);
    }
}
