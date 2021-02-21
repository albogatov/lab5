package app;

import commands.*;
import exceptions.NonExistingCommandException;
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
    public static CommandCenter commandCenter;
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
     * @param cmd - команда
     */
    public void addCmd(Command cmd) {
        commands.put(cmd.getCommand(), cmd);
    }

    /**
     * Метод, распознающий команду в строке, введенной пользователем
     * @param cmdLine - строка, содержащая команду
     * @return - объект класса соответсвующей команды
     * @throws NonExistingCommandException - в случае ввода несуществующей команды
     */
    public Command getCmd(String cmdLine) throws NonExistingCommandException {
        if (!commands.containsKey(cmdLine)) {
            throw new NonExistingCommandException("Такой команды не существует");
        }
        return commands.getOrDefault(cmdLine, null);
    }

    public static CommandCenter getInstance() {
        if (commandCenter == null)
            return new CommandCenter();
        return commandCenter;
    }

    /**
     * Метод, возврашающий полный список всех команд
     * @return - список команд
     */
    public List<Command> retrieveAllCommands() {
        return commands.keySet().stream().map(commands::get).collect(Collectors.toList());
    }

    /**
     * Метод, вызывающий исполнение команды
     * @param ui - объект, через который ведется взаимодействие с пользователем
     * @param line - часть строки пользовательского ввода, содержающая команду
     * @param fullLine - полная строка ввода с аргументами
     * @param interactiveStorage - объект для взаимодействия с коллекцией
     * @throws IOException - в случае некорректного ввода
     * @throws NonExistingCommandException - в случае вызова не существующей программы
     */
    public void executeCommand(UserInterface ui, String line, String fullLine, InteractionInterface interactiveStorage) throws IOException, NonExistingCommandException {
        Command cmd = getCmd(line);
        String[] args = fullLine.split(" ");
        cmd.execute(ui, args, interactiveStorage);
    }

}
