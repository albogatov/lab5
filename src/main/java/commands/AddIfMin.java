package commands;

import app.Command;
import elements.Worker;
import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.IOException;

/**
 * Класс команды addIfMin
 */
public class AddIfMin extends Command {
    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды
     */
    public AddIfMin() {
        cmdLine = "addIfMin";
        description = "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции: " +
                "ввести имя, оклад сотрудника";
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
        int size1 = interactiveStorage.getSize();
        Worker worker = ui.readWorker(arguments, 1);
        interactiveStorage.addIfMin(worker);
        int size2 = interactiveStorage.getSize();
        if (size2 > size1)
            ui.displayMessage("Элемент успешно добавлен");
        else ui.displayMessage("Элемент не добавлен, т.к. он не подходит критерию");
    }
}
