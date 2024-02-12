package technical.commands.abstractions;

import technical.managers.AbstractCommandHandler;

public abstract class AbstractCommand implements Command{
    private String name;
    private String description;

    protected AbstractCommandHandler.ShellValuables shell;

    public AbstractCommand(String name, String description, AbstractCommandHandler.ShellValuables shell){
        this.name = name;
        this.description = description;
        this.shell = shell;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
