package commands;

import app.Command;
import elements.Worker;
import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.IOException;

/**
 * Класс команды update
 */
public class Update extends Command {
    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды
     */
    public Update() {
        cmdLine = "update";
        description = "обновить значение элемента коллекции, id которого равен заданному";
    }
    /**
     * Метод исполнения
     *
     * @param ui                 - объект, через который ведется взаимодействие с пользователем
     * @param arguments          - необходимые для исполнения аргументы
     * @param interactiveStorage - объект для взаимодействия с коллекцией
     * @throws IOException - в случае некорректного ввода
     */
    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException {
        long id = Long.parseLong(arguments[1]);
        Worker worker;
        if (interactiveStorage.findById(id)) {
            arguments = ui.reReadArguments("Введите имя и оклад сотрудника: ");
            if (arguments[0].isEmpty() || arguments[1].isEmpty()) {
                do {
                    arguments = ui.reReadArguments("Введите имя и оклад сотрудника: ");
                } while (arguments[0].isEmpty() || arguments[1].isEmpty());
            }
            worker = ui.readWorker(arguments, 0);
            interactiveStorage.update(id, worker);
            ui.displayMessage("Сотрудник обновлен");
        }
        else System.out.println("Сотрудника с таким идентификатором нет");
    }
}
