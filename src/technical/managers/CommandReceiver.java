package technical.managers;

import com.fasterxml.jackson.core.JsonProcessingException;
import necessary.Movie;
import technical.commands.abstractions.AbstractCommand;
import technical.commands.abstractions.Command;
import technical.exceptions.FileException;
import technical.exceptions.RecursionException;
import technical.exceptions.WrongArgumentException;
import technical.managers.abstractions.AbstractCommandHandler;
import technical.managers.abstractions.AbstractReceiver;
import technical.managers.abstractions.IOutputManager;

import java.io.*;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

import static technical.Utils.isInt;

public class CommandReceiver extends AbstractReceiver {
    public CommandReceiver(AbstractCommandHandler.ShellValuables shell){
        super(shell);
    }

    @Override
    public void add(String[] args){
        Movie element = Movie.createMovie1(shell.getInputManager(), shell.getOutputManager());

        try {
            shell.getCollectionManager().add(element);
        } catch (WrongArgumentException e){
            throw new WrongArgumentException("");
        }
    }

    @Override
    public void save(String[] args) {
        try {
            FileManager fm = shell.getFileManager();
            fm.writeToFile(shell.getCollectionManager().getCollection());
        } catch (JsonProcessingException e) {
            shell.getOutputManager().print("Не записать в файл:\n" + e.getMessage());
        }
    }

    @Override
    public void clear(String[] args) {
        shell.getCollectionManager().clear();
        shell.getOutputManager().print("Коллекция очищена.");
    }

    @Override
    public void show(String[] args) {
        shell.getOutputManager().print(shell.getCollectionManager().presentView());
    }

    @Override
    public void exit(String[] args) {
        System.exit(0);
    }

    @Override
    public void info(String[] args) {
        IOutputManager output = shell.getOutputManager();
        Map<String, String> info = shell.getCollectionManager().getInfo();
        output.print("Информация о коллекции:");

        for(String key : info.keySet()){
            output.print("\t" + key + " - " + info.get(key));
        }
    }

    @Override
    public void executeScript(String[] args) {
        if (args[0].isBlank()) {
            shell.getOutputManager().print("Некорректные аргументы.");
            return;
        }

        if (!args[0].endsWith(".txt")) {
            throw new FileException("Указан файл недопустимого формата.");
        }

        File file = new File(args[0]);
        if (!file.exists() || !file.isFile()){
            throw new FileException("Нет файла с указанным именем");
        } else if (!file.canRead()){
            throw new FileException("Файл недоступен для чтения.");
        }

        String writer = "";

        try (BufferedReader bufReader = new BufferedReader(new FileReader(file))) {
            String temp;
            while ((temp = bufReader.readLine()) != null){
                if (temp.strip().startsWith("execute_script") && temp.strip().substring(14).strip().startsWith(args[0])){
//                    throw new RecursionException();
                    shell.getOutputManager().print("В файле рекурсия. Будут выполнены все остальные команды.");
                } else writer = writer + temp;
            }

//            IInputManager oldInputManager = shell.getInputManager();
//            shell.setTemporaryInputManager(new InputManager(new FileReader(file)));
            CharArrayReader car = new CharArrayReader(writer.toCharArray());

            shell.getInputManager().setTemporaryInput(new BufferedReader(car));
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

    @Override
    public void filterByGoldenPalmCount(String[] args) {
        if (!isInt(args[0]) && !args[0].isBlank()){
            shell.getOutputManager().print("Некорректные аргументы.");
        }
        Integer gp_count = args[0].isBlank() ? null : Integer.parseInt(args[0]);

        Vector<Movie> collection = shell.getCollectionManager().getCollection();

        if (collection.isEmpty()){
            shell.getOutputManager().print("Коллекция пуста.");
            return;
        }

        for (Movie i : collection){
            if (Objects.equals(i.getGoldenPalmCount(), gp_count)){
                shell.getOutputManager().print(i.toString());
            }
        }
    }

    @Override
    public void help(String[] args) {
        Map<String, AbstractCommand> commandsList = shell.commands;
        
        /*if (!s[0].isBlank() && commandsList.containsKey(s[0].strip())){
            System.out.println("Справка по команде: " + s[0].strip());
            System.out.printf("\t%s: \t%s\n", s[0].strip(), commandsList.get(s[0].strip()).getDescription());
        }
        else {
            System.out.println("Список доступных команд.");
            for (String name : commandsList.keySet()) {
                System.out.printf("\t%s: \t%s\n", name, commandsList.get(name).getDescription());
            }
        }*/
        IOutputManager output = shell.getOutputManager();

        output.print("Список доступных команд.");
        for (String name : commandsList.keySet()) {
            String temp = name;
            if (!commandsList.get(name).getArguments().equals("no")) temp += " " + commandsList.get(name).getArguments();
            output.print(String.format("\t%s: \t%s", temp, commandsList.get(name).getDescription()));
        }
    }

    @Override
    public void history(String[] args) {
        IOutputManager output = shell.getOutputManager();

        output.print("[");
        for(Command i : shell.getHistoryManager().getHistory()){
            output.print("\t" + i.getName());
        }
        output.print("]");
    }

    @Override
    public void minByCoordinates(String[] args) {
        Vector<Movie> collection = shell.getCollectionManager().getCollection();

        if (collection.isEmpty()){
            shell.getOutputManager().print("Коллекция пуста.");
            return;
        }

        Movie min = null;
        for (Movie i : collection){
            if (min == null || min.getCoordinates().compareTo(i.getCoordinates()) > 0){
                min = i;
            }
        }
        shell.getOutputManager().print(min.toString());
    }

    @Override
    public void removeAllByGoldenPalmCount(String[] args) {
        if (!isInt(args[0]) && !args[0].isBlank()){
            shell.getOutputManager().print("Некорректные аргументы.");
        }
        Integer gp_count = args[0].isBlank() ? null : Integer.parseInt(args[0]);

        Vector<Movie> collection = shell.getCollectionManager().getCollection();
        for (Movie i : collection){
            if (Objects.equals(i.getGoldenPalmCount(), gp_count)){
                shell.getCollectionManager().remove(i);
            }
        }
    }

    @Override
    public void removeById(String[] args) {
        if (!isInt(args[0])){
            shell.getOutputManager().print("Некорректные аргументы.");
        }
        int id = Integer.parseInt(args[0]);

        Vector<Movie> collection = shell.getCollectionManager().getCollection();
        for (int i = 0; i < collection.size(); i++){
            if (collection.get(i).getId() == id){
                shell.getCollectionManager().remove(i);
                shell.getOutputManager().print("Элемент c id=" + id + "удален.");
                return;
            }
        }
        shell.getOutputManager().print("В коллекции нет элемента с id=" + id + ".");
    }

    @Override
    public void removeFirst(String[] args) {
        if (shell.getCollectionManager().getCollection().isEmpty()){
            shell.getOutputManager().print("Коллекция пуста.");
            return;
        }
        shell.getCollectionManager().removeFirst();
        shell.getOutputManager().print("Элемент удален.");
    }

    @Override
    public void removeLower(String[] args) {
        CollectionManager cm = shell.getCollectionManager();
        Vector<Movie> collection = cm.getCollection();

        if (collection.isEmpty()){
            shell.getOutputManager().print("Коллекция пуста.");
            return;
        }
        Movie elem = Movie.createMovie1(shell.getInputManager(), shell.getOutputManager());
        for(Movie i : collection){
            if (i.compareTo(elem) < 0){
                cm.remove(i);
            }
        }
    }

    @Override
    public void update(String[] args) {
        if (!isInt(args[0])){
            shell.getOutputManager().print("Некорректные аргументы.");
            return;
        }
        int id = Integer.parseInt(args[0]);

        Vector<Movie> collection = shell.getCollectionManager().getCollection();
        for (Movie i : collection){
            if (i.getId() == id){
                i.update(Movie.createMovie1(shell.getInputManager(), shell.getOutputManager()));
                shell.getOutputManager().print("Элемент c id=" + id + " обновлён.");
                return;
            }
        }
        shell.getOutputManager().print("В коллекции нет элемента с id=" + id + ".");
    }
}
