package commands;

import app.Command;
import interaction.InteractionInterface;
import interaction.UserInterface;
import elements.Worker;

import java.io.IOException;

/**
 * Класс команды add.
 */
public class Add extends Command {
    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды.
     */
    public Add() {
        cmdLine = "add";
        description = "Добавить новый элемент в коллекцию.";
    }

    /**
     * Метод исполнения
     *
     * @param ui                 объект, через который ведется взаимодействие с пользователем.
     * @param arguments          необходимые для исполнения аргументы.
     * @param interactiveStorage объект для взаимодействия с коллекцией.
     * @throws IOException в случае ошибки ввода/вывода.
     */
    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws Exception {
        Worker worker = ui.readWorker(ui);
        int initSize = interactiveStorage.getSize();
        interactiveStorage.add(worker);
        if(interactiveStorage.getSize() > initSize)
            ui.displayMessage("Сотрудник успешно добавлен");
        else ui.displayMessage("Такой сотрудник уже добавлен");
    }
}
