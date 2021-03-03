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
        String confirmation;
        if (interactiveStorage.checkChanges()) {
            confirmation = ui.readUnlimitedArgument("Запрошен выход из приложения без сохранения. " +
                    "Введите: yes, если хотите выйти без сохранения; no, если хотите продолжить работу; " +
                    "save, если хотите сохранить коллекцию и выйти", false);
            switch (confirmation) {
                case "yes":
                    ui.displayMessage("До новых встреч");
                    System.exit(0);
                case "no":
                    ui.displayMessage("Работа с коллекцией возобновлена");
                    break;
                case "save":
                    interactiveStorage.save();
                    ui.displayMessage("Коллекция сохранена, до новых встреч");
                    System.exit(0);
            }
        } else {
            ui.displayMessage("До новых встреч");
            System.exit(0);
        }
    }
}
