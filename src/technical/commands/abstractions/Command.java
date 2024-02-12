package technical.commands.abstractions;

import technical.managers.CollectionManager;

public interface Command {
    public void execute(String[] s);
}
