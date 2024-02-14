package necessary;

import necessary.enums.*;
import technical.exceptions.InterruptException;
import technical.managers.abstractions.IInputManager;
import technical.managers.abstractions.IOutputManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.util.Date birthday; //Поле не может быть null
    private EyeColor eyeColor; //Поле может быть null
    private HairColor hairColor; //Поле не может быть null
    private Country nationality; //Поле не может быть null
    private Location location; //Поле не может быть null

    protected Person() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setEyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setHairColor(HairColor hairColor) {
        this.hairColor = hairColor;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public static Person createPerson(IInputManager input, IOutputManager output){
        Person elem = new Person();

        try{
            while (true) {
                try {
                    output.print("\nВведите имя режиссёра и дату его рождения (ДД.ММ.ГГГГ) через запятую: ");
                    String[] args = input.nextLine().split(",");

                    if (!args[0].isBlank()) {
                        elem.setName(args[0].strip());
                    } else {
                        output.print("Имя не может быть пустым.");
                        continue;
                    }
                    if (args[1].strip().matches("(0[1-9]|[12][0-9]|3[01]).(0[1-9]|1[012]).(1[89]\\d\\d|20([01]\\d|2[01234]))")) {
                        String[] temp = args[1].split("\\.");

                        int day = Integer.parseInt(temp[0].strip());
                        int month = Integer.parseInt(temp[1].strip());
                        int year = Integer.parseInt(temp[2].strip());

                        elem.setBirthday(new Date(year, month, day));
                        break;
                    } else {
                        output.print("Неправильный формат даты.");
                    }
                } catch (RuntimeException e){
                    output.print(e.getMessage());
                    output.print("Неправильный формат аргументов");
                }
            }

            while (true) {
                output.print("\nЦвет глаз режиссёра - BLUE, YELLOW, ORANGE, WHITE или BROWN: ");
                String color = input.nextLine().strip();
                if (EyeColor.contains(color)){
                    elem.setEyeColor(EyeColor.valueOf(color));
                    break;
                } else{
                    output.print("Недопустимый цвет глаз.");
                }
            }

            while (true) {
                output.print("\nЦвет волос режиссёра - GREEN, RED, BLUE, YELLOW или ORANGE: ");
                String color = input.nextLine().strip();
                if (HairColor.contains(color)){
                    elem.setHairColor(HairColor.valueOf(color));
                    break;
                } else{
                    output.print("Недопустимый цвет глаз.");
                }
            }

            while (true) {
                output.print("\nСтрана рождения режиссёра - FRANCE, INDIA, VATICAN или THAILAND: ");
                String line = input.nextLine().strip();
                if (Country.contains(line)){
                    elem.setNationality(Country.valueOf(line));
                    break;
                } else{
                    output.print("Недопустимая страна.");
                }
            }

            while (true) {
                output.print("\nМестонахождение режиссёра - дробное число и два целых через запятую: ");
                try {
                    String[] line = input.nextLine().strip().replace(" ", "").split(",");

                    if (line[0].matches("-?\\d*.\\d*") && line[1].matches("-?\\d*")
                            && line[2].matches("-?\\d*")) {
                        elem.setLocation(new Location(Float.parseFloat(line[0]), Long.parseLong(line[1]),
                                Integer.parseInt(line[2])));
                        break;
                    } else {
                        output.print("Недопустимый формат.");
                    }
                } catch (RuntimeException e){
                    System.out.println(e.getMessage());
                    System.out.println("Неправильный формат аргументов.");
                }
            }
            return elem;
        } catch (IOException e){
            output.print("Что-то случилось, введите команду заново.");
            throw new InterruptException();
        }
    }

    @Override
    public String toString() {
        return name + " (" + nationality.name() + ") born in " + birthday.getYear();
    }
}
