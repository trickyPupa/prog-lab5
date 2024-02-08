package technical.commands;

import technical.Command;

public class SaveCommand extends Command {
    public SaveCommand(){
        super("save");
    }

    @Override
    public boolean execute(String[] s) {
        return false;
    }
}
