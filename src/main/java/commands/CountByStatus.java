package commands;

import app.Command;
import elements.Status;
import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.IOException;

public class CountByStatus extends Command {

    public CountByStatus() {
        cmdLine = "countByStatus";
        description = "вывести количество элементов, значение поля status которых равно заданному";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException {
        Status status = Status.valueOf(arguments[1].toUpperCase());
        int result = interactiveStorage.count_by_status(status);
        ui.displayMessage("Элементов с таким статусом: " + String.valueOf(result));
    }
}
