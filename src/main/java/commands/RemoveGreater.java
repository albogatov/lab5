package commands;

import app.Command;
import elements.Worker;
import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.IOException;

/**
 * Класс команды removeGreater
 */
public class RemoveGreater extends Command {
    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды
     */
    public RemoveGreater() {
        cmdLine = "removeGreater";
        description = "удалить из коллекции все элементы, превышающие заданный";
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
        Worker worker = ui.readWorker();
        interactiveStorage.removeGreater(worker);
        int size2 = interactiveStorage.getSize();
        if (size2 < size1)
            ui.displayMessage("Операция успешно выполнена");
    }
}
