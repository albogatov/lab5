package commands;

import app.Command;
import app.CommandCenter;
import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

public class Help extends Command {
    public Help() {
        cmdLine = "help";
        description = "Вывести справку по доступным командам";
    }
    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException {
        for(Command cmd : CommandCenter.getInstance().retrieveAllCommands()) {
            ui.displayMessage(cmd.getCommand() + ": " + cmd.getHelp());
        }
    }
}
