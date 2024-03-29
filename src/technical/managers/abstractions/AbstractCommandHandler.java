package technical.managers.abstractions;

import technical.commands.abstractions.AbstractCommand;
import technical.managers.CollectionManager;
import technical.managers.CommandReceiver;
import technical.managers.FileManager;
import technical.managers.HistoryManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCommandHandler implements Handler {
    public class ShellValuables {
        private IInputManager inputManager;
        private IOutputManager outputManager;
        private final HistoryManager historyManager;
        private CollectionManager collectionManager;
        private FileManager fileManager;

        public int recur_param = 0;
        public int cur_recur_param = 0;

        public final Map<String, AbstractCommand> commands = new HashMap<>();

        public ShellValuables(IInputManager inp, IOutputManager out, CollectionManager col,
                              FileManager fm, HistoryManager history){
            inputManager = inp;
            outputManager = out;
            collectionManager = col;
            historyManager = history;
            fileManager = fm;
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

        public FileManager getFileManager() {
            return fileManager;
        }

        public Handler getCommandHander(){
            return AbstractCommandHandler.this;
        }
    }

    protected ShellValuables vals;

    protected AbstractReceiver receiver;

    public AbstractCommandHandler(IInputManager inp, IOutputManager out, CollectionManager col, FileManager fm){
        vals = new ShellValuables(inp, out, col, fm, new HistoryManager());
    }
}
