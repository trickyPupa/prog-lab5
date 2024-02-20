package technical.commands.implementations;

import technical.commands.abstractions.AbstractCommand;
import technical.commands.abstractions.Command;
import technical.managers.CollectionManager;
import technical.managers.abstractions.AbstractCommandHandler;
import technical.managers.abstractions.IOutputManager;

public class HistoryCommand extends AbstractCommand {
    public HistoryCommand(AbstractCommandHandler.ShellValuables shell) {
        super("history","Команда для вывода последних 5 выполненных команд (без аргументов).",
                "no", shell);
    }

    @Override
    public void execute(String[] s) {
        IOutputManager output = shell.getOutputManager();

        output.print("[");
        for(Command i : shell.getHistoryManager().getHistory()){
            output.print("\t" + i.getName());
        }
        output.print("]");
    }
}
