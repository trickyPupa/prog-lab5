import technical.managers.CommandHandler;
import technical.managers.Handler;
import technical.managers.InputManager;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        command_executing();
        String[] words = {"sdmak"};
        String[] a = Arrays.copyOfRange(words, 1, words.length);
        for (String i : a){
            System.out.println(i);
        }
    }

    public static void command_executing(){
        InputStream input = new BufferedInputStream(System.in);
        InputManager inputManager = new InputManager(input);
        Handler handler = new CommandHandler(inputManager);

        try(input){
            while (true){
                try {
                    handler.nextCommand();
                } catch (RuntimeException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    // maven посмотреть (авто сборка jar)
}