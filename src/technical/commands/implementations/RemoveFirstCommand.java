package technical.commands.implementations;

import technical.commands.abstractions.AbstractCommand;
import technical.managers.abstractions.AbstractCommandHandler;

public class RemoveFirstCommand extends AbstractCommand {
    public RemoveFirstCommand(AbstractCommandHandler.ShellValuables shell) {
        super("remove_first", "Команда для удаления первого элемента коллекции.",
                "no", shell);
    }

    @Override
    public void execute(String[] s) {
        if (shell.getCollectionManager().getCollection().isEmpty()){
            shell.getOutputManager().print("Коллекция пуста.");
            return;
        }
        shell.getCollectionManager().removeFirst();
        shell.getOutputManager().print("Элемент удален.");
    }
}
