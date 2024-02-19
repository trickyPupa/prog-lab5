package technical.commands.implementations;

import necessary.Movie;
import technical.commands.abstractions.AbstractCommand;
import technical.managers.abstractions.AbstractCommandHandler;

import java.util.Vector;

import static technical.Utils.isInt;

public class RemoveByIdCommand extends AbstractCommand {
    public RemoveByIdCommand(AbstractCommandHandler.ShellValuables shell) {
        super("remove_by_id", "Команда для удаления элемента коллекции с заданным id.",
                "id", shell);
    }

    @Override
    public void execute(String[] s) {
        if (!isInt(s[0])){
            shell.getOutputManager().print("Некорректные аргументы.");
        }
        int id = Integer.parseInt(s[0]);

        Vector<Movie> collection = shell.getCollectionManager().getCollection();
        for (int i = 0; i < collection.size(); i++){
            if (collection.get(i).getId() == id){
                shell.getCollectionManager().remove(i);
                shell.getOutputManager().print("Элемент c id=" + id + "удален.");
                return;
            }
        }
        shell.getOutputManager().print("В коллекции нет элемента с id=" + id + ".");
    }
}
