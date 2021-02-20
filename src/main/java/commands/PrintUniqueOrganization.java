package commands;

import app.Command;
import interaction.InteractionInterface;
import exceptions.MoreArgumentsRequiredException;
import interaction.UserInterface;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class PrintUniqueOrganization extends Command {

    public PrintUniqueOrganization() {
        cmdLine = "printUniqueOrganization";
        description = "вывести уникальные значения поля organization всех элементов в коллекции";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException, MoreArgumentsRequiredException {
        List<String> result = interactiveStorage.print_unique_organization();
        Iterator itr = result.iterator();
        while (itr.hasNext()) {
            ui.displayMessage(itr.next().toString());
        }
    }
}
