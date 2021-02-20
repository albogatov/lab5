package commands;

import app.Command;
import interaction.InteractionInterface;
import exceptions.MoreArgumentsRequiredException;
import interaction.UserInterface;

import java.io.IOException;

public class PrintAscending extends Command {

    public PrintAscending() {
        cmdLine = "printAscending";
        description = "вывести элементы коллекции в порядке возрастания";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException, MoreArgumentsRequiredException {
        interactiveStorage.print_ascending();
    }
}
