package app;

import commands.*;
import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс отвечающий за распознование и вызов команд
 */
public class CommandCenter {
    /**
     * Объект центра управления командами
     */
    public static CommandCenter commandCenter;
    /**
     * Список всех возможных команд
     */
    private final HashMap<String, Command> commands = new HashMap<>();

    /**
     * Конструктор центра комманд, где добавляются все возможные команды
     */
    public CommandCenter() {
        addCmd(new Add());
        addCmd(new Help());
        addCmd(new AddIfMin());
        addCmd(new Info());
        addCmd(new Show());
        addCmd(new Save());
        addCmd(new Update());
        addCmd(new RemoveById());
        addCmd(new Clear());
        addCmd(new ExecuteScript());
        addCmd(new Exit());
        addCmd(new RemoveGreater());
        addCmd(new RemoveLower());
        addCmd(new CountByStatus());
        addCmd(new PrintAscending());
        addCmd(new PrintUniqueOrganization());
    }

    /**
     * Метод передачи команды в конструктор
     *
     * @param cmd Команда
     */
    public void addCmd(Command cmd) {
        commands.put(cmd.getCommand(), cmd);
    }

    /**
     * Метод, распознающий команду в строке, введенной пользователем
     *
     * @param cmdLine Строка, содержащая команду
     * @return Объект класса соответсвующей команды
     */
    public Command getCmd(String cmdLine) {
        return commands.getOrDefault(cmdLine, null);
    }

    /**
     * Метод, возвращающий единственный объект класса. Реализация шаблона "Синглтон".
     *
     * @return Объект центра управления командами
     */
    public static CommandCenter getInstance() {
        if (commandCenter == null)
            return new CommandCenter();
        return commandCenter;
    }

    /**
     * Метод, возврашающий полный список всех команд
     *
     * @return Список команд
     */
    public List<Command> retrieveAllCommands() {
        return commands.keySet().stream().map(commands::get).collect(Collectors.toList());
    }

    /**
     * Метод, вызывающий исполнение команды
     *
     * @param ui                 объект, через который ведется взаимодействие с пользователем
     * @param line               часть строки пользовательского ввода, содержающая команду
     * @param fullLine           полная строка ввода с аргументами
     * @param interactiveStorage объект для взаимодействия с коллекцией
     * @throws IOException в случае ошибки ввода/вывода
     */
    public void executeCommand(UserInterface ui, String line, String fullLine, InteractionInterface interactiveStorage) throws IOException {
        Command cmd = getCmd(line);
        String[] args = fullLine.split(" ");
        cmd.execute(ui, args, interactiveStorage);
    }

}
