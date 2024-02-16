package necessary;

import necessary.enums.*;
import technical.exceptions.WrongArgumentException;
import technical.managers.OutputManager;
import technical.managers.abstractions.IInputManager;
import technical.managers.abstractions.IOutputManager;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

import static technical.Utils.*;

public class Movie implements Comparable {
    private static int id_counter = 0;

    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    private String name; //Поле не может быть null, Строка не может быть пустой
    private int oscarsCount; //Значение поля должно быть больше 0
    private Integer goldenPalmCount; //Значение поля должно быть больше 0, Поле может быть null
    private long length; //Значение поля должно быть больше 0

    private Coordinates coordinates; //Поле не может быть null
    private MpaaRating mpaaRating; //Поле не может быть null
    private Person director; //Поле не может быть null

    protected Movie(){
        id_counter++;
        id = id_counter;
        creationDate = java.time.LocalDate.now();
    }

    private void setArgs(String[] args) {
        for (int i = 0; i < 4; i++){
            switch (i){
                case 0:
                    this.name = args[i].strip();
                    break;
                case 1:
                    this.oscarsCount = Integer.parseInt(args[i].strip());
                    break;
                case 2:
                    this.goldenPalmCount = Integer.parseInt(args[i].strip());
                    break;
                case 3:
                    this.length = Integer.parseInt(args[i].strip());
            }
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOscarsCount(int oscarsCount) {
        this.oscarsCount = oscarsCount;
    }

    public void setGoldenPalmCount(Integer goldenPalmCount) {
        this.goldenPalmCount = goldenPalmCount;
    }

    public void setLength(long length) {
        this.length = length;
    }

    private void setDirector(Person director) {
        this.director = director;
    }

    private void setMpaaRating(MpaaRating mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    private void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public static boolean validateArgs(String[] args){
        String[] queue = {"str", "int", "Int", "int"};

        for (int i = 0; i < 4; i++){
            if (queue[i].equals("str") && args[i].isBlank()){
                return false;
            } else if (queue[i].equals("int") && !args[i].strip().matches("\\d*")) {
                return false;
            } else if (queue[i].equals("Int") && !(args[i].isBlank() || args[i].strip().matches("\\d*"))) {
                return false;
            }
        }
        return true;
    }

    public static Movie createMovie(String[] args, IInputManager input, IOutputManager output){
        Movie elem = new Movie();

        if (!validateArgs(args)){
            throw new WrongArgumentException();
        }
        elem.setArgs(args);

        try {
            while (true) {
                output.print("\nКоординаты фильма - целое число больше -879, и целое число не больше 155: ");
                String[] arg = input.nextLine().strip().replace(" ", "").split(",");

                if (!(arg[0].matches("-?\\d*") && arg[1].matches("-?\\d*"))){
                    output.print("Недопустимый формат.");
                } else {
                    int a = Integer.parseInt(arg[0]);
                    long b = Long.parseLong(arg[1]);
                    if (a <= -879 || b > 155){
                        output.print("Некорректные данные");
                        continue;
                    }

                    elem.setCoordinates(new Coordinates(a, b));
                    break;
                }
            }

            while (true) {
                output.print("\nMPAA рейтинг фильма - PG, PG_13 или NC_17: ");
                String arg = input.nextLine().strip();
                if (MpaaRating.contains(arg)){
                    elem.setMpaaRating(MpaaRating.valueOf(arg));
                    break;
                } else{
                    output.print("Некорректный рейтинг.");
                }
            }
            elem.setDirector(Person.createPerson(input, output));
        } catch (IOException e){
            System.out.println(e.getMessage());
            // додумать как обрабатывать
        }

        return elem;
    }

    public static Movie createMovie1(IInputManager input, IOutputManager output){
        Movie elem = new Movie();

        Map<String, Predicate<String>> args_checkers = new LinkedHashMap<>();
        args_checkers.put("имя", x -> {
            if (!x.isBlank()){
                elem.setName(x);
                return true;
            }
            return false;
        });
        args_checkers.put("количество премий Оскар", x -> {
            if (isInt(x) && !x.equals("0")){
                elem.setOscarsCount(Integer.parseInt(x));
                return true;
            }
            return false;
        });
        args_checkers.put("количество золотых пальмовых ветвей (может быть 0)", x -> {
            if (isInt(x)){
                elem.setGoldenPalmCount(Integer.parseInt(x));
                return true;
            }
            return false;
        });
        args_checkers.put("продолжительность фильма", x -> {
            if (isLong(x) && !x.equals("0")){
                elem.setLength(Long.parseLong(x));
                return true;
            }
            return false;
        });
        args_checkers.put("MPAA рейтинг фильма (PG, PG_13, NC_17)", x -> {
            if (MpaaRating.contains(x)){
                elem.setMpaaRating(MpaaRating.valueOf(x));
                return true;
            }
            return false;
        });


        // "Имя режиссёра", "Дата рождения режиссёра (ДД.ММ.ГГГГ)", "Цвет глаз режиссёра (BLUE, YELLOW, ORANGE, WHITE, BROWN)"

        try {
            for (String a : args_checkers.keySet()){
                Predicate<String> check = args_checkers.get(a);
                output.print("Введите " + a + ":");
                String line = input.nextLine();

                while (!check.test(line)){
                    output.print("Некорректные данные.");
                    output.print("Введите " + a + ":");
                    line = input.nextLine();
                }
            }

            elem.setCoordinates(Coordinates.createCoords(input, output));
            elem.setDirector(Person.createPerson1(input, output));

        } catch (IOException e){
            output.print(e.getMessage());
        }

        return elem;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public String toString() {
        return name + " by " + director.toString() + " with " + oscarsCount + " Oscars and " +
                goldenPalmCount + " Golden Palms";
    }
}
