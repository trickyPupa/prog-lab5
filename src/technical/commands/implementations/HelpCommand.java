package technical.commands.implementations;

import technical.commands.abstractions.AbstractCommand;
import technical.managers.abstractions.AbstractCommandHandler;
import technical.managers.abstractions.AbstractReceiver;
import technical.managers.abstractions.IOutputManager;

import java.util.Map;

public class HelpCommand extends AbstractCommand {
    public HelpCommand(){
        super("help", "Команда для вывода справки по всем доступным командам.", "no");
    }

    @Override
    public void execute(String[] s, AbstractReceiver rec) {
        rec.help(s);
    }
}
