package commands;

import app.Command;
import interaction.InteractionInterface;
import exceptions.MoreArgumentsRequiredException;
import interaction.UserInterface;

import java.io.IOException;

public class Show extends Command {

    public Show() {
        cmdLine = "show";
        description = "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException, MoreArgumentsRequiredException {
        ui.displayMessage("Коллекция:");
        interactiveStorage.show();
    }
}
