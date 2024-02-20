package technical.managers.abstractions;

import java.io.IOException;

public interface Handler {
    /**
     * Пытается считать и выполнить следующую команду.
     * @throws IOException
     */
    public void nextCommand() throws IOException;
    /**
     * Выполняет командуЮ данную в качестве параметра.
     * @param commandName - название команды
     * @throws IOException
     */
    public void nextCommand(String commandName);
}
