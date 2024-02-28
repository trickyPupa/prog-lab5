package technical.commands.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import necessary.Movie;
import technical.commands.abstractions.AbstractCommand;
import technical.managers.FileManager;
import technical.managers.abstractions.AbstractCommandHandler;
import technical.managers.abstractions.AbstractReceiver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class SaveCommand extends AbstractCommand {
    public SaveCommand(){
        super("save", "Команда для сохранения текущей версии коллекции в файл.", "no");
    }

    @Override
    public void execute(String[] s, AbstractReceiver rec) {
        rec.save(s);
    }
}
