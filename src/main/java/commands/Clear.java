package commands;

import app.Command;
import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.IOException;

/**
 * Класс команды clear
 */
public class Clear extends Command {
    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды
     */
    public Clear() {
        cmdLine = "clear";
        description = "очистить коллекцию";
    }
    /**
     * Метод исполнения
     * @param ui - объект, через который ведется взаимодействие с пользователем
     * @param arguments - необходимые для исполнения аргументы
     * @param interactiveStorage - объект для взаимодействия с коллекцией
     */
    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) {
        interactiveStorage.clear();
        if (interactiveStorage.getSize() > 0)
            System.out.println("Что-то пошло не так, попробуйте еще раз");
        else System.out.println("Коллекция очищена");
    }
}
