package technical.commands.implementations;

import necessary.Movie;
import technical.commands.abstractions.AbstractCommand;
import technical.exceptions.WrongArgumentException;
import technical.exceptions.WrongCommandSyntax;
import technical.managers.AbstractCommandHandler;

import java.io.IOException;
import java.util.Arrays;

public class AddCommand extends AbstractCommand{

    public AddCommand(AbstractCommandHandler.ShellValuables shell) {
        super("add", "Команда для добавления Movie в коллекцию.\nПотребуется ввести все характеристики" +
                " фильма через запятую:\n - Название (строка), количество \"Оскаров\" (целое число), количество " +
                "золотых пальмовых ветвей (целое число), продолжительность фильма (целое число)\n - Координаты" +
                " на отдельной строке (2 целых числа: первое больше -879, второе  не больше 155)\n - MPAA" +
                " рейтинг фильма на отдельной строке (одна из строк: PG, PG_13, NC_17)\n - Режиссер фильма" +
                " на отдельной строке: имя, дата рождения в формате ДД.ММ.ГГГГ, на отдельной строке цвет глаз" +
                " (одна строка из: BLUE, YELLOW, ORANGE, WHITE, BROWN.), на отдельной строке цвет волос (одна " +
                "из строк: GREEN, RED, BLUE, YELLOW, ORANGE), на отдельной строке национальность (одна из " +
                "доступных стран: FRANCE, INDIA, VATICAN, THAILAND), на отдельной строке местонахождение (3 " +
                "числа-координаты: дробное, целое, целое)", shell);
    }

    @Override
    public void execute(String[] s) {
        Movie element = Movie.createMovie(s, shell.getInputManager(), shell.getOutputManager());
        try {
            shell.getCollectionManager().add(element);
        } catch (WrongArgumentException e){
            throw new WrongArgumentException(getName());
        }
    }
}
