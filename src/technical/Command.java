package technical;

public abstract class Command {
    private String name;
    private String description;

    public Command(String name){
        this.name = name;
    }
    public Command(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract boolean execute(String[] s);
}
