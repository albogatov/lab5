package app;

import exceptions.NonExistingCommandException;
import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.IOException;

/**
 * Абстрактный класс, от которого наследуются все команды
 */
public abstract class Command {
    protected String cmdLine;
    protected String description;

    /**
     * Метод исполнения команды
     * @param ui - объект, через который ведется взаимодействие с пользователем
     * @param arguments - необходимые для исполнения аргументы
     * @param interactiveStorage - объект для взаимодействия с коллекцией
     * @throws IOException - в случае некорректного ввода
     * @throws NonExistingCommandException - в случае ввода несуществующей команды
     */
    public abstract void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException, NonExistingCommandException;

    /**
     * Стандартный конструктор
     */
    public Command() {

    }

    /**
     * Возвращает строку, вызывающую команду
     * @return - строка
     */
    public String getCommand() {
        return cmdLine;
    }

    /**
     * Возвращает описание команды
     * @return - описание
     */
    public String getHelp() {
        return description;
    }
}
