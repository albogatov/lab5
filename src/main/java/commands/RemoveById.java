package commands;

import app.Command;
import interaction.InteractionInterface;

import interaction.UserInterface;

import java.io.IOException;

/**
 * Класс команды remove_by_id.
 */
public class RemoveById extends Command {
    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды.
     */
    public RemoveById() {
        cmdLine = "remove_by_id";
        description = "удалить элемент из коллекции по его id";
    }

    /**
     * Метод исполнения
     *
     * @param ui                 объект, через который ведется взаимодействие с пользователем.
     * @param arguments          необходимые для исполнения аргументы.
     * @param interactiveStorage объект для взаимодействия с коллекцией.
     * @throws IOException в случае ошибки ввода/вывода.
     */
    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException {
        long id = Long.parseLong(arguments[1]);
        if (interactiveStorage.findById(id)) {
            interactiveStorage.removeById(id);
            ui.displayMessage("Сотрудник удален");
        }
    }
}
