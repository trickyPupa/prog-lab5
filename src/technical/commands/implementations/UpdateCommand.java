package technical.commands.implementations;

import necessary.Movie;
import technical.commands.abstractions.AbstractCommand;
import technical.managers.abstractions.AbstractCommandHandler;
import static technical.Utils.isInt;

import java.util.Arrays;
import java.util.Vector;

public class UpdateCommand extends AbstractCommand {
    public UpdateCommand(AbstractCommandHandler.ShellValuables shell) {
        super("update", "Команда для обновления значений элемента коллекции с заданным id.",
                "id {element}", shell);
    }

    @Override
    public void execute(String[] s) {
        if (!isInt(s[0])){
            shell.getOutputManager().print("Некорректные аргументы.");
        }
        int id = Integer.parseInt(s[0]);

        Vector<Movie> collection = shell.getCollectionManager().getCollection();
        for (Movie i : collection){
            if (i.getId() == id){
                i.update(Movie.createMovie1(shell.getInputManager(), shell.getOutputManager()));
                shell.getOutputManager().print("Элемент c id=" + id + " обновлён.");
                return;
            }
        }
        shell.getOutputManager().print("В коллекции нет элемента с id=" + id + ".");
    }
}
