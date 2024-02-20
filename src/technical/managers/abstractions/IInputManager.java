package technical.managers.abstractions;

import necessary.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Базовый интерфейс для обработчиков входных потоков для программы.
 * @see technical.managers.InputManager
 */
public interface IInputManager {
    public String nextLine() throws IOException;
    public void setTemporaryInput(Reader input);
    public void setTemporaryInput(InputStream input);
}
