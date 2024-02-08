package technical;

import technical.commands.*;

import java.io.IOException;
import java.io.InputStream;
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
    }

    private final InputStream input;

    public CommandHandler(InputStream inp){
        this.input = inp;
    }

    public String nextLine() throws IOException {
        String line = "";
        char c;
        while ((c = (char)(input.read())) != '\n'){
            line = line + c;
        }
        return line;
    }

    @Override
    public boolean nextCommand() throws IOException {
        String line = nextLine();
        String[] words = line.split(" ");
        Command currentCommand = commands.get(words[0]);

        return currentCommand.execute(Arrays.copyOfRange(words, 1, words.length));

//        return false;
    }

    @Override
    public boolean start() {
        return false;
    }
}
