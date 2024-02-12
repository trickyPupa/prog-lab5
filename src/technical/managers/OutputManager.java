package technical.managers;

import technical.managers.abstractions.IOutputManager;

public class OutputManager implements IOutputManager {
    @Override
    public void print(String s) {
        System.out.println(s);
    }
}
