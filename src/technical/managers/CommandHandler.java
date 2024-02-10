package technical.managers;

import technical.Command;
import technical.commands.*;
import technical.exceptions.NoSuchCommandException;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class CommandHandler implements Handler {
    public static final String[] command_names = {"help", "info", "show", "add", "update id", "remove_by_id",
            "clear", "save", "execute_script", "exit", "remove_first", "remove_lower", "history",
            "remove_all_by_golden_palm_count", "min_by_coordinates", "filter_by_golden_palm_count"};

    public static final HashMap<String, Command> commands = new HashMap<>();
    static {
        commands.put("help", new HelpCommand());
        commands.put("save", new SaveCommand());
        commands.put("exit", new ExitCommand());
        commands.put("add", new AddCommand());
    }

    private final InputManager input;
    private HistoryManager history;

    public CommandHandler(InputManager inp){
        this.input = inp;
        this.history = new HistoryManager();
    }

    @Override
    public void nextCommand() throws IOException {
        String line = input.nextLine();
        String[] words = line.split(" ");
        if (commands.containsKey(words[0])){
            throw new NoSuchCommandException(line);
        }
        Command currentCommand = commands.get(words[0]);

        currentCommand.execute(Arrays.copyOfRange(words, 1, words.length));

//        return false;
    }

    @Override
    public void start(){
        ;
    }
}
