package technical.commands.implementations;

import technical.commands.abstractions.AbstractCommand;
import technical.managers.abstractions.AbstractCommandHandler;
import technical.managers.abstractions.AbstractReceiver;
import technical.managers.abstractions.IOutputManager;

import java.util.Map;

public class InfoCommand extends AbstractCommand {
    public InfoCommand(){
        super("info", "Команда для вывода информации о коллекции.", "no");
    }

    @Override
    public void execute(String[] s, AbstractReceiver rec) {
        rec.info(s);
    }
}
