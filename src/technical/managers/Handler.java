package technical.managers;

import java.io.IOException;

public interface Handler {
    public void nextCommand() throws IOException;
    public void start();
}
