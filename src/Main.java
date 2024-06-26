import necessary.Movie;
import necessary.enums.Country;
import technical.exceptions.*;
import technical.managers.*;
import technical.managers.abstractions.Handler;
import technical.managers.abstractions.IInputManager;
import technical.managers.abstractions.IOutputManager;

import java.io.*;


public class Main {
    public static void main(String[] args) {
//        test();

//        test1();

        commandExecuting(args);
    }


    public static void test(){
        String filename = "C:\\Users\\timof\\IdeaProjects\\prog-lab5\\data\\data1.json";
        try(InputStream input = new BufferedInputStream(System.in)) {
            IInputManager inputManager = new InputManager(input);
            IOutputManager outputManager = new OutputManager();
            CollectionManager collectionManager = new CollectionManager();
            FileManager fileManager = new FileManager(filename);

            Handler handler = new CommandHandler(inputManager, outputManager, collectionManager, fileManager);

            inputManager.setTemporaryInput(new FileReader(new File("C:\\Users\\timof\\IdeaProjects\\prog-lab5\\data\\script.txt")));
            while(true) {

                System.out.println("'" + inputManager.nextLine() + "'");
                if (inputManager.nextLine() == null){
                    break;
                }
            }

        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println(e);
        }
    }

    public static void test1(){
        System.out.println(Integer.parseInt("   "));
    }

    public static void commandExecuting(String[] args){
        String filename = args[0];

//        InputStream input = new BufferedInputStream(System.in);

        try(InputStream input = new BufferedInputStream(System.in)){
            IInputManager inputManager = new InputManager(input);
            IOutputManager outputManager = new OutputManager();
            FileManager fileManager = new FileManager(filename);
            // CollectionManager collectionManager = new CollectionManager();
            CollectionManager collectionManager = new CollectionManager(fileManager.collectionFromFile());
            int start_id = 0;
            for(Movie m : collectionManager.getCollection()){
                if (m.getId() > start_id) start_id = m.getId();
            }

            Movie.setId_counter(start_id);

            Handler handler = new CommandHandler(inputManager, outputManager, collectionManager, fileManager);

            while (true){
                try {
                    handler.nextCommand();
                } catch (WrongArgumentException e){
                    outputManager.print(e.toString());
                } catch (InterruptException e){
                    outputManager.print("Ввод данных остановлен.");
                } catch (NoSuchCommandException e){
                    outputManager.print("Нет доступной команды.");
//                    outputManager.print(e.getMessage());
                } catch (RecursionException e) {
                    outputManager.print("Рекурсия в исполняемом файле.");
                } catch (RuntimeException e){
                    outputManager.print(e.getMessage());
                    System.out.println("main catch runtime");
                }
            }
        }
        catch (FileException e){
            System.out.println("Ошибка с файлом.");
            System.out.println(e.getMessage());
        }
        catch(IOException e){
            System.out.println("Ошибка при чтении данных");
            System.out.println(e.getMessage());
            System.out.println("main catch io");
            throw new RuntimeException(e);
        }
        catch(Exception e){
            System.out.println("Что-то пошло не так в ходе выполнения программы.");
            System.out.println(e.getMessage());
        }
    }
}
