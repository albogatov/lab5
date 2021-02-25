package commands;

import app.Command;
import interaction.InteractionInterface;
import interaction.UserInterface;
import elements.Worker;

import java.io.IOException;

/**
 * Класс команды add
 */
public class Add extends Command {
    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды
     */
    public Add() {
        cmdLine = "add";
        description = "Добавить новый элемент в коллекцию. При вызове команды необходимо " +
                "ввести имя, оклад сотрудника";
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
        Worker worker = ui.readWorker(arguments, 1);
        interactiveStorage.add(worker);
        ui.displayMessage("Сотрудник успешно добавлен");
    }
}
