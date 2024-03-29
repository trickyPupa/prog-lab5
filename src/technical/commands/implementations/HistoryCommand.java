package technical.commands.implementations;

import technical.commands.abstractions.AbstractCommand;
import technical.commands.abstractions.Command;
import technical.managers.CollectionManager;
import technical.managers.abstractions.AbstractCommandHandler;
import technical.managers.abstractions.AbstractReceiver;
import technical.managers.abstractions.IOutputManager;

public class HistoryCommand extends AbstractCommand {
    public HistoryCommand() {
        super("history","Команда для вывода последних 5 выполненных команд (без аргументов).",
                "no");
    }

    @Override
    public void execute(String[] s, AbstractReceiver rec) {
        rec.history(s);
    }
}
