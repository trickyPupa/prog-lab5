package technical.managers;

import technical.commands.abstractions.AbstractCommand;
import technical.commands.abstractions.Command;
import technical.commands.implementations.*;
import technical.exceptions.NoSuchCommandException;
import technical.managers.abstractions.Handler;
import technical.managers.abstractions.IInputManager;
import technical.managers.abstractions.IOutputManager;

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
    }

    public CommandHandler(IInputManager inp, IOutputManager out, CollectionManager col){
        super(inp, out, col);
    }

    @Override
    public void nextCommand() throws IOException {
        IInputManager input = vals.getInputManager();

        String line = input.nextLine();
        String commandName = line.contains(" ") ? line.substring(0, line.indexOf(" ")).strip() : line.strip();

        if (!vals.commands.containsKey(commandName)){
            throw new NoSuchCommandException(line);
        }
        Command currentCommand = vals.commands.get(commandName);

        currentCommand.execute(line.substring(line.indexOf(commandName) + commandName.length()).split(","));
    }

    @Override
    public void start(){
        ;
    }
}
