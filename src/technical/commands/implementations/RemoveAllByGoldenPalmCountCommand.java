package technical.commands.implementations;

import necessary.Movie;
import technical.commands.abstractions.AbstractCommand;
import technical.managers.CollectionManager;
import technical.managers.abstractions.AbstractCommandHandler;
import technical.managers.abstractions.AbstractReceiver;

import java.util.Objects;
import java.util.Vector;

import static technical.Utils.isInt;

public class RemoveAllByGoldenPalmCountCommand extends AbstractCommand {
    public RemoveAllByGoldenPalmCountCommand() {
        super("remove_all_by_golden_palm_count", "Команда для удаления всех элементов коллекции, " +
                        "с заданным количеством золотых пальмовых ветвей.",
                "goldenPalmCount");
    }

    @Override
    public void execute(String[] s, AbstractReceiver rec) {
        rec.removeAllByGoldenPalmCount(s);
    }
}
