import necessary.Person;
import technical.managers.*;
import technical.managers.abstractions.Handler;
import technical.managers.abstractions.IInputManager;
import technical.managers.abstractions.IOutputManager;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
//        command_executing();
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