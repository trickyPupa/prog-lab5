package technical.commands.implementations;

import necessary.Movie;
import technical.commands.abstractions.AbstractCommand;
import technical.managers.abstractions.AbstractCommandHandler;

import java.util.Objects;
import java.util.Vector;

import static technical.Utils.isInt;

public class FilterByGoldenPalmCountCommand extends AbstractCommand {
    public FilterByGoldenPalmCountCommand(AbstractCommandHandler.ShellValuables shell) {
        super("filter_by_golden_palm_count", "Команда для вывода элементов коллекции с заданным " +
                        "количество золотых пальмовых ветвей.",
                "goldenPalmCount", shell);
    }

    @Override
    public void execute(String[] s) {
        if (!isInt(s[0]) && !s[0].isBlank()){
            shell.getOutputManager().print("Некорректные аргументы.");
        }
        Integer gp_count = s[0].isBlank() ? null : Integer.parseInt(s[0]);

        Vector<Movie> collection = shell.getCollectionManager().getCollection();

        if (collection.isEmpty()){
            shell.getOutputManager().print("Коллекция пуста.");
            return;
        }

        for (Movie i : collection){
            if (Objects.equals(i.getGoldenPalmCount(), gp_count)){
                shell.getOutputManager().print(i.toString());
            }
        }
    }
}