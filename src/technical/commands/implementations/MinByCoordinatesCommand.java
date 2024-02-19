package technical.commands.implementations;

import necessary.Movie;
import technical.commands.abstractions.AbstractCommand;
import technical.managers.abstractions.AbstractCommandHandler;

import java.util.Objects;
import java.util.Vector;

public class MinByCoordinatesCommand extends AbstractCommand {
    public MinByCoordinatesCommand(AbstractCommandHandler.ShellValuables shell) {
        super("min_by_coordinates", "Команда для вывода элемента коллекции с минимальными координатами.",
                "no", shell);
    }

    @Override
    public void execute(String[] s) {
        Vector<Movie> collection = shell.getCollectionManager().getCollection();

        if (collection.isEmpty()){
            shell.getOutputManager().print("Коллекция пуста.");
            return;
        }

        Movie min = null;
        for (Movie i : collection){
            if (min == null || min.getCoordinates().compareTo(i.getCoordinates()) > 0){
                min = i;
            }
        }
        shell.getOutputManager().print(min.toString());
    }
}
