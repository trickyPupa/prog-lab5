package technical.commands.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import necessary.Movie;
import technical.commands.abstractions.AbstractCommand;
import technical.managers.FileManager;
import technical.managers.abstractions.AbstractCommandHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class SaveCommand extends AbstractCommand {
    public SaveCommand(AbstractCommandHandler.ShellValuables shell){
        super("save", "Команда для сохранения текущей версии коллекции в файл.", "no", shell);
    }

    @Override
    public void execute(String[] s) {
        try {
            FileManager fm = shell.getFileManager();
            fm.writeToFile(shell.getCollectionManager().getCollection());
        } catch (JsonProcessingException e) {
            shell.getOutputManager().print("Не записать в файл:\n" + e.getMessage());
        }
    }
}
