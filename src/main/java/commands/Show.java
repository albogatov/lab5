package commands;

import app.Command;
import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.IOException;

/**
 * Класс команды show
 */
public class Show extends Command {
    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды
     */
    public Show() {
        cmdLine = "show";
        description = "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
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
        if (interactiveStorage.getSize() == 0)
            ui.displayMessage("Коллекция пуста");
        else {
            ui.displayMessage("Коллекция:");
            interactiveStorage.show();
        }
    }
}
