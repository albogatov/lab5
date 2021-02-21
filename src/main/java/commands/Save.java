package commands;

import app.Command;
import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.IOException;

/**
 * Класс команды save
 */
public class Save extends Command {
    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды
     */
    public Save() {
        cmdLine = "save";
        description = "сохранить коллекцию в файл";
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
        String filePath = arguments[1];
        interactiveStorage.save(filePath);
        ui.displayMessage("Коллекция сохранена в файл");
    }
}
