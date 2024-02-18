import com.fasterxml.jackson.core.JsonProcessingException;
import necessary.Movie;
import necessary.Person;

import technical.exceptions.FileException;
import technical.exceptions.WrongArgumentException;
import technical.managers.*;
import technical.managers.abstractions.Handler;
import technical.managers.abstractions.IInputManager;
import technical.managers.abstractions.IOutputManager;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.SortedMap;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        test();

//        test1();

//        command_executing(args);
    }

    public static void test(){
        ;
    }

    public static void test1(){
        InputStream input = new BufferedInputStream(System.in);
        IInputManager inputManager = new InputManager(input);
        IOutputManager outputManager = new OutputManager();

        Movie m = Movie.createMovie1(inputManager, outputManager);
        System.out.println(m);
//        FileManager a = new FileManager();

//        try (FileWriter fw = new FileWriter(new File("C:\\Users\\timof\\IdeaProjects\\prog-lab5\\data.json"))) {
////            fw.write(a.elemToFile(m));
//            Vector<Movie> vec = new Vector<>();
//            vec.add(m);
//            fw.write(a.objectToFile(vec));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public static void command_executing(String[] args){
        String filename = args[0];

//        InputStream input = new BufferedInputStream(System.in);

        try(InputStream input = new BufferedInputStream(System.in)){
            IInputManager inputManager = new InputManager(input);
            IOutputManager outputManager = new OutputManager();
            CollectionManager collectionManager = new CollectionManager();
            FileManager fileManager = new FileManager(filename);

            Handler handler = new CommandHandler(inputManager, outputManager, collectionManager, fileManager);

            while (true){
                try {
                    handler.nextCommand();
                } catch (WrongArgumentException e){
                    outputManager.print(e.toString());
                } catch (RuntimeException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        catch (FileException e){
            System.out.println("Ошибка с файлом.");
            System.out.println(e.getMessage());
        }
        catch(IOException e){
            System.out.println("Что-то пошло не так при вводе данных.");
            System.out.println(e.getMessage());
        }
        catch(Exception e){
            System.out.println("Что-то пошло не так в ходе выполнения программы.");
            System.out.println(e.getMessage());
        }
    }

    // maven посмотреть (авто сборка jar)
}