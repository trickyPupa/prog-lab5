package technical.managers;

import technical.Command;

import java.util.ArrayDeque;

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
}
