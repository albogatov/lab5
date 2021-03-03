package commands;

import app.Command;
import app.CommandCenter;
import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.IOException;

/**
 * Класс команды help.
 */
public class Help extends Command {
    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды.
     */
    public Help() {
        cmdLine = "help";
        description = "Вывести справку по доступным командам";
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
        for (Command cmd : CommandCenter.getInstance().retrieveAllCommands()) {
            ui.displayMessage(cmd.getCommand() + ": " + cmd.getHelp());
        }
    }
}
