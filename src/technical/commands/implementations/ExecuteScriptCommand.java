package technical.commands.implementations;

import technical.commands.abstractions.AbstractCommand;
import technical.exceptions.FileException;
import technical.exceptions.RecursionException;
import technical.managers.abstractions.AbstractCommandHandler;
import technical.managers.abstractions.IInputManager;

import java.io.*;

public class ExecuteScriptCommand extends AbstractCommand {
    public ExecuteScriptCommand(AbstractCommandHandler.ShellValuables shell) {
        super("execute_script", "Команда для исполнения скрипта из заданного файла.",
                "file_name", shell);
    }

    @Override
    public void execute(String[] s) {
        if (s[0].isBlank()) {
            shell.getOutputManager().print("Некорректные аргументы.");
        }

        if (!s[0].endsWith(".txt")) {
            throw new FileException("Указан файл недопустимого формата.");
        }

        File file = new File(s[0]);
        if (!file.exists() || !file.isFile()){
            throw new FileException("Нет файла с указанным именем");
        } else if (!file.canRead()){
            throw new FileException("Файл недоступен для чтения.");
        }

        try (BufferedReader bufReader = new BufferedReader(new FileReader(file))) {
            String temp;
            while ((temp = bufReader.readLine()) != null){
                temp = temp.strip();
                if (temp.startsWith("execute_script") && temp.substring(14).strip().startsWith(s[0])){
                    throw new RecursionException();
                }
            }

//            IInputManager oldInputManager = shell.getInputManager();
//            shell.setTemporaryInputManager(new InputManager(new FileReader(file)));

            shell.getInputManager().setTemporaryInput(new BufferedReader(new FileReader(file)));
        } catch (FileNotFoundException e) {
            throw new FileException("Нет файла с указанным именем");
        } catch (IOException e){
            System.out.println("Ошибка при чтении данных");
            System.out.println(e.getMessage());
        }

//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = reader.readLine()) != null){
//                shell.getCommandHander().nextCommand(line);
//            }
//            shell.getCommandHander().nextCommand("exit");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }
}
