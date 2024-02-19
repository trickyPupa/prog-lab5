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

public class CommandHandler extends AbstractCommandHandler implements Handler {
    public static final String[] command_names = {"help", "info", "show", "add", "update id", "remove_by_id",
            "clear", "save", "execute_script", "exit", "remove_first", "remove_lower", "history",
            "remove_all_by_golden_palm_count", "min_by_coordinates", "filter_by_golden_palm_count"};

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
    }

    public CommandHandler(IInputManager inp, IOutputManager out, CollectionManager col, FileManager fm){
        super(inp, out, col, fm);
    }

    @Override
    public void nextCommand() throws IOException {
        IInputManager input = vals.getInputManager();

        String line = input.nextLine().strip();
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
    public void start(){
        ;
    }
}
