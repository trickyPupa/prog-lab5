package technical.managers;

import technical.commands.abstractions.Command;

import java.util.ArrayDeque;
import java.util.Collection;

public class HistoryManager {
    private ArrayDeque<Command> history;

    public HistoryManager(){
        history = new ArrayDeque<>(5);
    }

    public void next(Command c){
        history.addLast(c);
        history.removeFirst();
    }

    public Command getLast(){
        return history.getLast();
    }

    public Collection<Command> getHistory(){
        return history;
    }
}
