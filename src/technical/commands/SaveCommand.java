package technical.commands;

import technical.Command;

public class SaveCommand extends Command {
    public SaveCommand(){
        super("save", "Команда для сохранения текущей версии коллекции в файл.");
    }

    @Override
    public void execute(String[] s) {
        ;
    }
}
