package necessary;

import necessary.enums.*;
import technical.exceptions.WrongArgumentException;
import technical.managers.OutputManager;
import technical.managers.abstractions.IInputManager;
import technical.managers.abstractions.IOutputManager;

import java.io.IOException;

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

    public Movie(){
        id_counter++;
        id = id_counter;
        creationDate = java.time.LocalDate.now();
    }

    public void setArgs(String line) {
        String[] args = line.split(",");
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

    public void setDirector(Person director) {
        this.director = director;
    }

    public void setMpaaRating(MpaaRating mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public static boolean validateArgs(String s){
        String[] args = s.split(",");
        String[] queue = {"str", "int", "Int", "int"};

        for (int i = 0; i < 4; i++){
            if (queue[i].equals("str") && args[i].isBlank()){
                return false;
            } else if (queue[i].equals("int") && !args[i].strip().matches("[\\d]")) {
                return false;
            } else if (queue[i].equals("Int") && !(args[i].isBlank() || args[i].strip().matches("\\d"))) {
                return false;
            }
        }
        return true;
    }

    public static Movie createMovie(String line, IInputManager input, IOutputManager output){
        Movie elem = new Movie();

        if (!validateArgs(line)){
            throw new WrongArgumentException();
        }
        elem.setArgs(line);

        try {
            while (true) {
                output.print("\nКоординаты фильма - целое число, большее -879, и целое число, не большее 155: ");
                String[] arg = input.nextLine().strip().replace(" ", "").split(",");

                if (!(arg[0].matches("\\d*") && arg[1].matches("\\d*"))){
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

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
