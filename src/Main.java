import necessary.Person;
import technical.exceptions.WrongArgumentException;
import technical.managers.*;
import technical.managers.abstractions.Handler;
import technical.managers.abstractions.IInputManager;
import technical.managers.abstractions.IOutputManager;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
//        test();

//        test1();

        command_executing();
    }

    public static void test(){
        InputStream input = new BufferedInputStream(System.in);
        IInputManager inputManager = new InputManager(input);
        IOutputManager outputManager = new OutputManager();
        CollectionManager collectionManager = new CollectionManager();

        Handler handler = new CommandHandler(inputManager, outputManager, collectionManager);

        Person.createPerson1(inputManager, outputManager);

    }

    public static void test1(){
        Vector<Integer> a = new Vector<>();
        a.add(12);
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
    }

    public static void command_executing(){
        InputStream input = new BufferedInputStream(System.in);
        IInputManager inputManager = new InputManager(input);
        IOutputManager outputManager = new OutputManager();
        CollectionManager collectionManager = new CollectionManager();

        Handler handler = new CommandHandler(inputManager, outputManager, collectionManager);

        try(input){
            while (true){
                try {
                    handler.nextCommand();
                } catch (WrongArgumentException e){
                    outputManager.print(e.toString());
                } catch (RuntimeException e){
                    System.out.println(e.toString());
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    // maven посмотреть (авто сборка jar)
}