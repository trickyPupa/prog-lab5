package technical.commands.implementations;

import technical.commands.abstractions.AbstractCommand;
import technical.managers.abstractions.AbstractCommandHandler;

public class SaveCommand extends AbstractCommand {
    public SaveCommand(AbstractCommandHandler.ShellValuables shell){
        super("save", "Команда для сохранения текущей версии коллекции в файл.", "no", shell);
    }

    @Override
    public void execute(String[] s) {
        ;
    }
}
