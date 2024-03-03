package technical.managers.abstractions;

/**
 * Базовый интерфейс для обработчиков выходных потоков для программы.
 * @see technical.managers.OutputManager
 */
public interface IOutputManager {
    /**
     * Печатает переданные данные в доступный поток вывода.
     * @param s данные для печати
     */
    public void print(String s);
}
