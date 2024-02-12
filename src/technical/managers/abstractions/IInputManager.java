package technical.managers.abstractions;

import necessary.Movie;

import java.io.IOException;

public interface IInputManager {
    public String nextLine() throws IOException;

    public String nextWord() throws IOException;
}
