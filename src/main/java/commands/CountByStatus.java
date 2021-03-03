package commands;

import app.Command;
import elements.Status;
import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.IOException;

/**
 * Класс команды count_by_status.
 */
public class CountByStatus extends Command {
    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды.
     */
    public CountByStatus() {
        cmdLine = "count_by_status";
        description = "вывести количество элементов, значение поля status которых равно заданному";
    }

    /**
     * Метод исполнения
     *
     * @param ui                 объект, через который ведется взаимодействие с пользователем
     * @param arguments          необходимые для исполнения аргументы
     * @param interactiveStorage объект для взаимодействия с коллекцией
     * @throws IOException в случае ошибки ввода/вывода
     */
    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException {
        Status status = Status.valueOf(arguments[1].toUpperCase());
        int result = interactiveStorage.countByStatus(status);
        ui.displayMessage("Элементов с таким статусом: " + result);
    }
}
