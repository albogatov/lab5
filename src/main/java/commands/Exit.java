package commands;

import app.Command;
import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.IOException;

/**
 * Класс команды exit
 */
public class Exit extends Command {
    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды
     */
    public Exit() {
        cmdLine = "exit";
        description = "выход из программы";
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
        String confirmation = ui.readNecessaryArgument("Запрошен выход из приложения без сохранения, вы хотите продолжить?");
        if (confirmation.equals("yes")) {
            ui.displayMessage("До новых встреч");
            System.exit(0);
        }
    }
}
