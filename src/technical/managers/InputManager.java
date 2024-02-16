package technical.managers;

import technical.managers.abstractions.IInputManager;

import java.io.*;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class InputManager implements IInputManager {
    InputStream input;
    Reader normalInput;
    public InputManager(InputStream input){
        this.input = input;
        normalInput = new InputStreamReader(input, StandardCharsets.UTF_8);
    }

    @Override
    public String nextLine() throws IOException {
        String line = "";
        char c;
        while ((c = (char)normalInput.read()) != '\n'){
            line = line + c;
        }
        return line.strip();
    }

    @Override
    public String nextWord() throws IOException {
        String word = "";
        char c;
        while ((c = (char)(normalInput.read())) != ' '){
            word = c == '\n' ? word + c : word;
        }
        return word;
    }
}
