package technical.managers;

import java.io.IOException;
import java.io.InputStream;

public class InputManager implements IInputManager{
    InputStream input;
    public InputManager(InputStream input){
        this.input = input;
    }

    @Override
    public String nextLine() throws IOException {
        String line = "";
        char c;
        while ((c = (char)(input.read())) != '\n'){
            line = line + c;
        }
        return line;
    }
}
