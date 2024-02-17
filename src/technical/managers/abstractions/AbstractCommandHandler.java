package technical.managers.abstractions;

import technical.commands.abstractions.AbstractCommand;
import technical.managers.CollectionManager;
import technical.managers.HistoryManager;
import technical.managers.abstractions.Handler;
import technical.managers.abstractions.IInputManager;
import technical.managers.abstractions.IOutputManager;

import java.util.HashMap;

public abstract class AbstractCommandHandler implements Handler {
    public class ShellValuables {
        private final IInputManager inputManager;
        private final IOutputManager outputManager;
        private final HistoryManager historyManager;
        private final CollectionManager collectionManager;

        public final HashMap<String, AbstractCommand> commands = new HashMap<>();

        public ShellValuables(IInputManager inp, IOutputManager out, CollectionManager col, HistoryManager history){
            inputManager = inp;
            outputManager = out;
            collectionManager = col;
            historyManager = history;
        }

        public IInputManager getInputManager() {
            return inputManager;
        }

        public HistoryManager getHistoryManager() {
            return historyManager;
        }

        public CollectionManager getCollectionManager() {
            return collectionManager;
        }

        public IOutputManager getOutputManager() {
            return outputManager;
        }
    }

    protected ShellValuables vals;

    public AbstractCommandHandler(IInputManager inp, IOutputManager out, CollectionManager col){
        vals = new ShellValuables(inp, out, col, new HistoryManager());
    }
}
