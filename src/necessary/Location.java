package necessary;

import technical.exceptions.InterruptException;
import technical.managers.abstractions.IInputManager;
import technical.managers.abstractions.IOutputManager;

import java.io.IOException;
import java.util.concurrent.atomic.LongAccumulator;

import static technical.Utils.*;

public class Location implements Checkable {
    private float x;
    private Long y;
    private Integer z;

    public Location(){

    }

    public Location(float a, long b, int c){
        x = a;
        y = b;
        z = c;
    }

    public static Location createLocation(IInputManager input, IOutputManager output){
        try{
            float a;
            long b;
            int c;

            while(true) {
                output.print("Введиите координату X локации режиссёра (число с плавающей точкой с количеством знаков <10^38): ");
                String line = input.nextLine();
                if (line == null || line.equals("exit")){
                    throw new InterruptException();
                }
                if (isFloat(line)){
                    a = Float.parseFloat(line);
                    break;
                }
                output.print("Некорректные данные.");
            }

            while(true) {
                output.print("Введиите координату Y локации режиссёра (целое число <9*10^18 и >-9*10^18): ");
                String line = input.nextLine();
                if (line == null || line.equals("exit")){
                    throw new InterruptException();
                }
                if (isLong(line)){
                    b = Long.parseLong(line);
                    break;
                }
                output.print("Некорректные данные.");
            }

            while(true) {
                output.print("Введиите координату Z локации режиссёра (целое число <2*10^9 и >-2*10^9): ");
                String line = input.nextLine();
                if (line == null || line.equals("exit")){
                    throw new InterruptException();
                }
                if (isInt(line)){
                    c = Integer.parseInt(line);
                    break;
                }
                output.print("Некорректные данные.");
            }

            return new Location(a, b, c);

        } catch (IOException e){
            output.print(e.getMessage());
            output.print("Что-то случилось, введите команду заново.");
            throw new InterruptException();
        }
    }

    @Override
    public boolean checkItself(){
        return y != null && z != null;
    }
}
