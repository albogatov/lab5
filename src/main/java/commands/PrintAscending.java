package commands;

import app.Command;
import interaction.InteractionInterface;
import interaction.UserInterface;

/**
 * Класс команды printAscending
 */
public class PrintAscending extends Command {
    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды
     */
    public PrintAscending() {
        cmdLine = "printAscending";
        description = "вывести элементы коллекции в порядке возрастания";
    }

    /**
     * Метод исполнения
     *
     * @param ui                 объект, через который ведется взаимодействие с пользователем
     * @param arguments          необходимые для исполнения аргументы
     * @param interactiveStorage объект для взаимодействия с коллекцией
     */
    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) {
        interactiveStorage.printAscending();
    }
}
