package technical.commands.implementations;

import necessary.Movie;
import technical.commands.abstractions.AbstractCommand;
import technical.managers.CollectionManager;
import technical.managers.abstractions.AbstractCommandHandler;

import java.util.Vector;

public class RemoveLowerCommand extends AbstractCommand {
    public RemoveLowerCommand(AbstractCommandHandler.ShellValuables shell) {
        super("remove_lower", "Команда для удаления всех элементов коллекции, меньших чем заданный.",
                "{element}", shell);
    }

    @Override
    public void execute(String[] s) {
        CollectionManager cm = shell.getCollectionManager();
        Vector<Movie> collection = cm.getCollection();

        if (collection.isEmpty()){
            shell.getOutputManager().print("Коллекция пуста.");
            return;
        }
        Movie elem = Movie.createMovie1(shell.getInputManager(), shell.getOutputManager());
        for(Movie i : collection){
            if (i.compareTo(elem) < 0){
                cm.remove(i);
            }
        }
    }
}
