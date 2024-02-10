package technical.commands;

import technical.Command;

public class ExitCommand extends Command {

    public ExitCommand() {
        super("exit", "Команда для завершения работы.");
    }

    @Override
    public void execute(String[] s) {
        System.exit(0);
    }
}
