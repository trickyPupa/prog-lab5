package technical;

import java.io.IOException;

public interface Handler {
    public boolean nextCommand() throws IOException;
    public boolean start();
}
