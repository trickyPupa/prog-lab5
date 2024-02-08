package technical;

import java.io.InputStream;

public class ConsoleReceiver implements Receiver{
    private InputStream input;
    public ConsoleReceiver(InputStream inp){
        this.input = inp;
    }

    public void setInput(InputStream input) {
        this.input = input;
    }

    @Override
    public String nextLine() {
        return null;
    }
}
