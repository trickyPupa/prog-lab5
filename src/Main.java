import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import necessary.Movie;
import necessary.Person;

import technical.exceptions.FileException;
import technical.exceptions.InterruptException;
import technical.exceptions.NoSuchCommandException;
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
//        test();

//        test1();

        command_executing(args);
    }

    public static void test(){
        String filename = "C:\\Users\\timof\\IdeaProjects\\prog-lab5\\data1.json";
        try(InputStream input = new BufferedInputStream(System.in)) {
            IInputManager inputManager = new InputManager(input);
            IOutputManager outputManager = new OutputManager();
            CollectionManager collectionManager = new CollectionManager();
            FileManager fileManager = new FileManager(filename);

            Handler handler = new CommandHandler(inputManager, outputManager, collectionManager, fileManager);

            Vector<Movie> vec = fileManager.collectionFromFile();
            System.out.println(vec);
            fileManager.writeToFile(vec);

        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println(e);
        }
    }

    public static void test1(){
        System.out.println(Integer.parseInt(""));
    }

    public static void command_executing(String[] args){
        String filename = args[0];

//        InputStream input = new BufferedInputStream(System.in);

        try(InputStream input = new BufferedInputStream(System.in)){
            IInputManager inputManager = new InputManager(input);
            IOutputManager outputManager = new OutputManager();
            FileManager fileManager = new FileManager(filename);
            //CollectionManager collectionManager = new CollectionManager();
            CollectionManager collectionManager = new CollectionManager(fileManager.collectionFromFile());

            Handler handler = new CommandHandler(inputManager, outputManager, collectionManager, fileManager);

            while (true){
                try {
                    handler.nextCommand();
                } catch (WrongArgumentException e){
                    outputManager.print(e.toString());
                } catch (InterruptException e){
                    outputManager.print("Ввод данных остановлен.");
                } catch (NoSuchCommandException e){
                    outputManager.print("Нет доступной команды " + e.getMessage() + ".");
                } catch (RuntimeException e){
                    outputManager.print(e.getMessage());
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
}