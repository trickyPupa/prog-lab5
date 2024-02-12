package technical.managers;

import technical.commands.abstractions.AbstractCommand;

import java.util.ArrayDeque;

public class HistoryManager {
    private ArrayDeque<AbstractCommand> history;

    public HistoryManager(){
        history = new ArrayDeque<>(5);
    }

    public void next(AbstractCommand c){
        history.addLast(c);
        history.removeFirst();
    }

    public AbstractCommand getLast(){
        return history.getLast();
    }
}
