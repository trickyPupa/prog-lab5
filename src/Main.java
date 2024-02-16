import technical.exceptions.WrongArgumentException;
import technical.managers.*;
import technical.managers.abstractions.Handler;
import technical.managers.abstractions.IInputManager;
import technical.managers.abstractions.IOutputManager;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

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

        try {
            String a = inputManager.nextLine();
            System.out.println("вы ввели " + a);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    public static void test1(){
        String a = "-155";

        System.out.println(a.matches("-?\\d*]"));
        System.out.println(Integer.parseInt(a));
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