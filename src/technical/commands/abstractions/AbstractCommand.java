package technical.commands.abstractions;

import technical.managers.abstractions.AbstractCommandHandler;

/**
 * Абстрактный класс реализующие некоторые общие для всех команд аспекты: имя и описание команды.
 */
public abstract class AbstractCommand implements Command{
    private String name;
    private String arguments;
    private String description;

    protected AbstractCommandHandler.ShellValuables shell;

    public AbstractCommand(String name, String description, String arguments, AbstractCommandHandler.ShellValuables shell){
        this.name = name;
        this.arguments = arguments;
        this.description = description;
        this.shell = shell;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getArguments() {
        return arguments;
    }
}
