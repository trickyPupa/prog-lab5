package technical.managers;

import technical.commands.abstractions.Command;
import technical.commands.implementations.*;
import technical.exceptions.NoSuchCommandException;
import technical.managers.abstractions.AbstractCommandHandler;
import technical.managers.abstractions.Handler;
import technical.managers.abstractions.IInputManager;
import technical.managers.abstractions.IOutputManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Класс - обработчик команд программы; считывает команды из {@link IInputManager} и провоцирует их исполнение.
 */
public class CommandHandler extends AbstractCommandHandler implements Handler {
    {
        vals.commands.put("help", new HelpCommand(vals));
        vals.commands.put("save", new SaveCommand(vals));
        vals.commands.put("exit", new ExitCommand(vals));
        vals.commands.put("add", new AddCommand(vals));
        vals.commands.put("show", new ShowCommand(vals));
        vals.commands.put("info", new InfoCommand(vals));
        vals.commands.put("clear", new ClearCommand(vals));
        vals.commands.put("update", new UpdateCommand(vals));
        vals.commands.put("history", new HistoryCommand(vals));
        vals.commands.put("remove_first", new RemoveFirstCommand(vals));
        vals.commands.put("remove_by_id", new RemoveByIdCommand(vals));
        vals.commands.put("filter_by_golden_palm_count", new FilterByGoldenPalmCountCommand(vals));
        vals.commands.put("min_by_coordinates", new MinByCoordinatesCommand(vals));
        vals.commands.put("remove_all_by_golden_palm_count", new RemoveAllByGoldenPalmCountCommand(vals));
        vals.commands.put("remove_lower", new RemoveLowerCommand(vals));
        vals.commands.put("execute_script", new ExecuteScriptCommand(vals));
    }

    public CommandHandler(IInputManager inp, IOutputManager out, CollectionManager col, FileManager fm){
        super(inp, out, col, fm);
    }

    @Override
    public void nextCommand(String line) {
        String commandName;
        String[] args;
        if (line.contains(" ")){
            commandName = line.substring(0, line.indexOf(" ")).strip();
            args = line.substring(1 + commandName.length()).split(" ");
        } else{
            commandName = line.strip();
            args = new String[0];
        }

        if (!vals.commands.containsKey(commandName)){
            throw new NoSuchCommandException(line);
        }
        Command currentCommand = vals.commands.get(commandName);

        currentCommand.execute(args);
        vals.getHistoryManager().next(currentCommand);
    }

    @Override
    public void nextCommand() throws IOException {
        IInputManager input = vals.getInputManager();

        String line = input.nextLine().strip();

        nextCommand(line);
    }
}
