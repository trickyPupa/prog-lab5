package technical.managers;

import technical.commands.abstractions.AbstractCommand;
import technical.commands.abstractions.IInputNeed;
import technical.commands.implementations.AddCommand;
import technical.commands.implementations.ExitCommand;
import technical.commands.implementations.HelpCommand;
import technical.commands.implementations.SaveCommand;
import technical.exceptions.NoSuchCommandException;
import technical.managers.abstractions.Handler;
import technical.managers.abstractions.IInputManager;
import technical.managers.abstractions.IOutputManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class CommandHandler extends AbstractCommandHandler implements Handler {
    public static final String[] command_names = {"help", "info", "show", "add", "update id", "remove_by_id",
            "clear", "save", "execute_script", "exit", "remove_first", "remove_lower", "history",
            "remove_all_by_golden_palm_count", "min_by_coordinates", "filter_by_golden_palm_count"};

    {
        vals.commands.put("help", new HelpCommand(vals));
        vals.commands.put("save", new SaveCommand(vals));
        vals.commands.put("exit", new ExitCommand(vals));
        vals.commands.put("add", new AddCommand(vals));
    }

    public CommandHandler(IInputManager inp, IOutputManager out, CollectionManager col){
        super(inp, out, col);
    }

    @Override
    public void nextCommand() throws IOException {
        IInputManager input = vals.getInputManager();

        String line = input.nextLine();
        String[] words = line.split(" ");

        if (vals.commands.containsKey(words[0])){
            throw new NoSuchCommandException(line);
        }
        AbstractCommand currentCommand = vals.commands.get(words[0]);

        currentCommand.execute(Arrays.copyOfRange(words, 1, words.length));
    }

    @Override
    public void start(){
        ;
    }
}
