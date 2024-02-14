package technical.managers;

import technical.managers.abstractions.IInputManager;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.CharsetDecoder;

public class InputManager implements IInputManager {
    InputStream input;
    public InputManager(InputStream input){
        this.input = input;
    }

    @Override
    public String nextLine() throws IOException {
        String line = "";
        char c;
        while ((c = (char)input.read()) != '\n'){
            line = line + c;
        }
        return line;
    }

    @Override
    public String nextWord() throws IOException {
        String word = "";
        char c;
        while ((c = (char)(input.read())) != ' '){
            word = c == '\n' ? word + c : word;
        }
        return word;
    }
}
