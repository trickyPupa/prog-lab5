package technical.commands.implementations;

import technical.commands.abstractions.AbstractCommand;
import technical.exceptions.FileException;
import technical.exceptions.RecursionException;
import technical.managers.abstractions.AbstractCommandHandler;
import technical.managers.abstractions.AbstractReceiver;
import technical.managers.abstractions.IInputManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.regex.Pattern;

public class ExecuteScriptCommand extends AbstractCommand {
    public ExecuteScriptCommand() {
        super("execute_script", "Команда для исполнения скрипта из заданного файла.",
                "file_name");
    }

    @Override
    public void execute(String[] s, AbstractReceiver rec) {
        rec.executeScript(s);

    }
}
