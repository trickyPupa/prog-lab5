package technical.commands.implementations;

import technical.commands.abstractions.AbstractCommand;
import technical.managers.abstractions.AbstractCommandHandler;
import technical.managers.abstractions.AbstractReceiver;

public class ClearCommand extends AbstractCommand {
    public ClearCommand(){
        super("clear", "Команда для очищения коллекции.", "no");
    }

    @Override
    public void execute(String[] s, AbstractReceiver rec) {
        rec.clear(s);
    }
}
