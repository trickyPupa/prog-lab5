package technical.managers;

import com.fasterxml.jackson.core.JsonProcessingException;
import necessary.Movie;
import technical.commands.abstractions.AbstractCommand;
import technical.commands.abstractions.Command;
import technical.exceptions.FileException;
import technical.exceptions.WrongArgumentException;
import technical.managers.abstractions.AbstractCommandHandler;
import technical.managers.abstractions.AbstractReceiver;
import technical.managers.abstractions.IOutputManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;
import java.util.regex.Pattern;

import static technical.Utils.isInt;

public class CommandReceiver extends AbstractReceiver {
    public CommandReceiver(AbstractCommandHandler.ShellValuables shell){
        super(shell);
    }

    @Override
    public void add(String[] args){
        Movie element = Movie.createMovie1(shell.getInputManager(), shell.getOutputManager());

        /*try {
            shell.getCollectionManager().add(element);
        } catch (WrongArgumentException e){
            throw new WrongArgumentException("");
        }*/
        shell.getCollectionManager().add(element);
        shell.getOutputManager().print("Новый элемент успешно добавлен");
    }

    @Override
    public void save(String[] args) {
        try {
            FileManager fm = shell.getFileManager();
            fm.writeToFile(shell.getCollectionManager().getCollection());

            shell.getOutputManager().print("Коллекция сохранена в файл.");
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
//            shell.getOutputManager().print("Некорректные аргументы.");
            throw new WrongArgumentException("execute");
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

        StringBuilder writer = new StringBuilder();

        try {
            int recur_param = checkRecursion(Path.of(args[0]), new ArrayDeque<>(), 0);
            if (shell.recur_param == 0) shell.recur_param = recur_param;

//            System.out.println(shell.recur_param);
//            System.out.println(shell.cur_recur_param);
//            System.out.println(recur_param);

            BufferedReader bufReader = new BufferedReader(new FileReader(file));

//            int check_recur_param = checkRecursion(Path.of(args[0]), new ArrayDeque<>(), 0);
//            System.out.println(check_recur_param);

//            if (recur_param != 0) {
//                shell.getOutputManager().print("При анализе скрипта обнаружена бесконечная рекурсия. Рекурсия внутри файла не будет выполнена.");
//            }

            String temp;
            while ((temp = bufReader.readLine()) != null){
                if (temp.strip().startsWith("execute_script")){ // && temp.strip().substring(14).strip().startsWith(args[0])
                    shell.cur_recur_param++;
                    if (shell.cur_recur_param == shell.recur_param) {
                        shell.getOutputManager().print("В файле обнаружена бесконечная рекурсия, " +
                                "будут выполнены все команды до нее.");
                        shell.recur_param = 0;
                        shell.cur_recur_param = 0;
                        break;
                    }

                }
                writer.append("\n").append(temp);
            }

            CharArrayReader car = new CharArrayReader(writer.toString().toCharArray());

            shell.getOutputManager().print("Начало исполнения файла {" + file.getPath() + "}.");
            shell.getInputManager().setTemporaryInput(new BufferedReader(car));

        } catch (FileNotFoundException e) {
            throw new FileException("Нет файла с указанным именем");
        } catch (IOException e){
            shell.getOutputManager().print("Ошибка при чтении данных.");
            shell.getOutputManager().print(e.getMessage());
        }
    }

    private int checkRecursion(Path path, ArrayDeque<Path> stack, int j) throws IOException {
        int i = 0;

        if (stack.contains(path)) return j;
        stack.addLast(path);
        String str = Files.readString(path);

        Pattern pattern = Pattern.compile("execute_script .*");
        var patternMatcher = pattern.matcher(str);
        while (patternMatcher.find())
        {
            i++;
            Path newPath = Path.of(patternMatcher.group().split(" ")[1]);
//            if(checkRecursion(newPath, stack, i) != 0) return i;
            int a = checkRecursion(newPath, stack, i);
            if (a != 0) return a + j;
        }
        stack.removeLast();
        return 0;
    }

    @Override
    public void filterByGoldenPalmCount(String[] args) {
        if (!isInt(args[0])){
//            shell.getOutputManager().print("Некорректные аргументы.");
            throw new WrongArgumentException("filter_by_golden_palm_count");
        }
        Integer gp_count = Integer.parseInt(args[0]);

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

        output.print("История команд [");
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
        if (!isInt(args[0])){
//            shell.getOutputManager().print("Некорректные аргументы.");
            throw new WrongArgumentException("remove_all_by_golden_palm_count");
        }
        Integer gp_count = Integer.parseInt(args[0]);

        Vector<Movie> collection = shell.getCollectionManager().getCollection();
        for (Movie i : collection){
            if (Objects.equals(i.getGoldenPalmCount(), gp_count)){
                shell.getCollectionManager().remove(i);
            }
        }

        shell.getOutputManager().print("Элементы с количеством золотых пальмовых ветвей = " + gp_count + " удалены.");
    }

    @Override
    public void removeById(String[] args) {
        if (!isInt(args[0])){
//            shell.getOutputManager().print("Некорректные аргументы.");
            throw new WrongArgumentException("remove_by_id");
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
                shell.getOutputManager().print("Удален элемент {" + i + "}.");
            }
        }
    }

    @Override
    public void update(String[] args) {
        if (!isInt(args[0])){
//            shell.getOutputManager().print("Некорректные аргументы.");
            throw new WrongArgumentException("update");
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
