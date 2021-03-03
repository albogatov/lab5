package commands;

import app.Command;
import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.IOException;
import java.util.List;

/**
 * Класс команды print_unique_organization.
 */
public class PrintUniqueOrganization extends Command {
    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды.
     */
    public PrintUniqueOrganization() {
        cmdLine = "print_unique_organization";
        description = "вывести уникальные значения поля organization всех элементов в коллекции";
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
        List<String> result = interactiveStorage.printUniqueOrganization();
        for (String o : result) {
            ui.displayMessage(o);
        }
    }
}
