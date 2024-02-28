package technical.managers.abstractions;

public abstract class AbstractReceiver {

    protected AbstractCommandHandler.ShellValuables shell;

    public AbstractReceiver(AbstractCommandHandler.ShellValuables shell){
        this.shell = shell;
    }

    public void add(String[] args){
        return;
    }

    public void save(String[] args){
        return;
    }

    public void clear(String[] args){
        return;
    }

    public void show(String[] args){
        return;
    }

    public void exit(String[] args){
        return;
    }

    public void info(String[] args){
        return;
    }

    public void executeScript(String[] args){
        return;
    }

    public void filterByGoldenPalmCount(String[] args){
        return;
    }

    public void help(String[] args){
        return;
    }

    public void history(String[] args){
        return;
    }

    public void minByCoordinates(String[] args){
        return;
    }

    public void removeAllByGoldenPalmCount(String[] args){
        return;
    }

    public void removeById(String[] args){
        return;
    }

    public void removeFirst(String[] args){
        return;
    }

    public void removeLower(String[] args){
        return;
    }

    public void update(String[] args){
        return;
    }
}
