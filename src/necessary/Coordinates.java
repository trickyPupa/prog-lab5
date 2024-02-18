package necessary;

import technical.managers.abstractions.IInputManager;
import technical.managers.abstractions.IOutputManager;

import java.io.IOException;

import static technical.Utils.isInt;
import static technical.Utils.isLong;

public class Coordinates implements Checkable {
    private int x;  // > -879
    private long y;  // <= 155

    public Coordinates(int a, long b){
        x = a;
        y = b;
    }
    private Coordinates(){}

    public void setX(int x) {
        this.x = x;
    }

    public void setY(long y) {
        this.y = y;
    }

    public static Coordinates createCoords(IInputManager input, IOutputManager output){
        Coordinates elem = new Coordinates();

        try{
            while(true) {
                output.print("Введиите координату X фильма (целое число больше -879): ");
                String line = input.nextLine();
                if (isInt(line) && Integer.parseInt(line) > -879){
                    elem.setX(Integer.parseInt(line));
                    break;
                }
                output.print("Некорректные данные.");
            }

            while(true) {
                output.print("Введиите координату Y фильма (целое число не больше 155): ");
                String line = input.nextLine();
                if (isLong(line) && Long.parseLong(line) <= 155){
                    elem.setY(Long.parseLong(line));
                    break;
                }
                output.print("Некорректные данные.");
            }

        } catch (IOException e){
            output.print(e.getMessage());
        }

        return elem;
    }

    @Override
    public boolean checkItself(){
        return x > -879 && y <= 155;
    }
}
