package technical.managers;

import technical.managers.abstractions.IInputManager;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Конкретный класс реализует {@link IInputManager}, осуществляет доставку входных данных программе из различных источников.
 */
public class InputManager implements IInputManager {
    protected InputStream input;
    protected BufferedReader normalInput;
    protected BufferedReader temporaryInput;

    public InputManager(InputStream input){
        this.input = input;
        normalInput = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
    }

    public InputManager(Reader input){
        normalInput = new BufferedReader(input);
    }

    /**
     * @return Возвращает следующую строку входных данных их текущего потока.
     * @throws IOException - если происходит ошибка ввода/вывода
     */
    @Override
    public String nextLine() throws IOException {
        String line = "";
        int c;

//        while ((char)(c = currentInput.read()) != '\n' && (char)c != '\r'){
//            if (c == -1) {
//                currentInput = normalInput;
//                return line;
//            }
//            line = line + (char)c;
//        }
//        return line.strip();

        if (temporaryInput != null){
            if ((line = temporaryInput.readLine()) != null) return line;
            else temporaryInput = null;
        }

        return normalInput.readLine().strip();
    }

    /*public String nextWord() throws IOException {
        String word = "";
        int c;
        while (true){
            if (temporaryInput == null || (c = temporaryInput.read()) == -1) {
//                (char) (c = (normalInput.read())) != ' '
                c = normalInput.read();
            }

            if (c == -1 || (char) c == '\n' || (char) c == ' ') return word.strip();
            word = word + (char)c;
        }
//        return word;
    }*/

    /**
     * Устанавливает поток, из которого требуется читать данные в обход основного. Когда поток исчерпается, произойдет возвращение к основному.
     * @param input новый поток
     */
    @Override
    public void setTemporaryInput(Reader input){
        temporaryInput = new BufferedReader(input);
    }

    @Override
    public void setTemporaryInput(InputStream input) {
        temporaryInput = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
    }
}
