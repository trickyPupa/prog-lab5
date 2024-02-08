import technical.Command;
import technical.CommandHandler;
import technical.Handler;

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
        Handler handler = new CommandHandler(input);

        try(input){
            while (true){
                handler.nextCommand();
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    // maven посмотреть (авто сборка jar)
}