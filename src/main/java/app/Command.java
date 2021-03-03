package app;

import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.IOException;

/**
 * Абстрактный класс, от которого наследуются все команды
 */
public abstract class Command {
    /**
     * Поле, содержащее строку для вызова команды
     */
    protected String cmdLine;
    /**
     * Поле, содержащее описание команды
     */
    protected String description;

    /**
     * Метод исполнения команды
     *
     * @param ui                 объект, через который ведется взаимодействие с пользователем
     * @param arguments          необходимые для исполнения аргументы
     * @param interactiveStorage объект для взаимодействия с коллекцией
     * @throws IOException в случае ошибки ввода/вывода
     */
    public abstract void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException;

    /**
     * Стандартный конструктор
     */
    public Command() {

    }

    /**
     * Возвращает строку, вызывающую команду
     *
     * @return Строка вызова команды
     */
    public String getCommand() {
        return cmdLine;
    }

    /**
     * Возвращает описание команды
     *
     * @return Описание команды
     */
    public String getHelp() {
        return description;
    }
}
