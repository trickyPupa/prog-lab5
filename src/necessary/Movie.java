package necessary;

import necessary.enums.*;

public class Movie implements Comparable {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int oscarsCount; //Значение поля должно быть больше 0
    private Integer goldenPalmCount; //Значение поля должно быть больше 0, Поле может быть null
    private long length; //Значение поля должно быть больше 0
    private MpaaRating mpaaRating; //Поле не может быть null
    private Person director; //Поле не может быть null

    public Movie(){
        ;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
