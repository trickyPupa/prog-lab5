package technical.commands.implementations;

import necessary.Movie;
import technical.commands.abstractions.AbstractCommand;
import technical.managers.abstractions.AbstractCommandHandler;
import technical.managers.abstractions.AbstractReceiver;

import java.util.Objects;
import java.util.Vector;

import static technical.Utils.isInt;

public class FilterByGoldenPalmCountCommand extends AbstractCommand {
    public FilterByGoldenPalmCountCommand() {
        super("filter_by_golden_palm_count", "Команда для вывода элементов коллекции с заданным " +
                        "количество золотых пальмовых ветвей.",
                "goldenPalmCount");
    }

    @Override
    public void execute(String[] s, AbstractReceiver rec) {
        rec.filterByGoldenPalmCount(s);
    }
}