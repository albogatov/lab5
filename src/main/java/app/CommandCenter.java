package app;

import commands.*;
import exceptions.NonExistingCommandException;
import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CommandCenter {
    static CommandCenter commandCenter;
    protected HashMap<String, Command> commands = new HashMap<String, Command>();
    protected ArrayList<String> listOfCommands = new ArrayList<>();

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

    public void addCmd(Command cmd) {
        commands.put(cmd.getCommand(), cmd);
    }

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

    public List<Command> retrieveAllCommands() {
        return commands.keySet().stream().map(x -> (commands.get(x))).collect(Collectors.toList());
    }

    public void executeCommand(UserInterface ui, String line, String fullLine, InteractionInterface interactiveStorage) throws IOException, NonExistingCommandException {
        Command cmd = getCmd(line);
        String[] args = fullLine.split(" ");
        cmd.execute(ui, args, interactiveStorage);
    }

    /** public Command retrieveCommand(String line) {
     Command cmd = getCmd(line);
     return cmd;
     } */
}
