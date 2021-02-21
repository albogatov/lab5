package commands;

import app.Command;
import interaction.InteractionInterface;
import interaction.UserInterface;

/**
 * Класс команды info
 */
public class Info extends Command {
    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды
     */
    public Info() {
        cmdLine = "info";
        description = "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
    /**
     * Метод исполнения
     * @param ui - объект, через который ведется взаимодействие с пользователем
     * @param arguments - необходимые для исполнения аргументы
     * @param interactiveStorage - объект для взаимодействия с коллекцией
     */
    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) {
        interactiveStorage.info();
    }
}
