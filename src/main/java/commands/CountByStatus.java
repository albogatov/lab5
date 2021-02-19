package commands;

import app.Command;
import elements.Status;
import interaction.InteractionInterface;
import exceptions.MoreArgumentsRequiredException;
import interaction.UserInterface;

import java.io.IOException;

public class CountByStatus extends Command {

    public CountByStatus() {
        cmdLine = "count_by_status";
        description = "вывести количество элементов, значение поля status которых равно заданному";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException, MoreArgumentsRequiredException {
        if(arguments.length < 2)
            throw new MoreArgumentsRequiredException("Введено слишком мало аргументов");
        Status status = Status.valueOf(arguments[1].toUpperCase());
        int result = interactiveStorage.count_by_status(status);
        ui.displayMessage("Элементов с таким статусом: " + String.valueOf(result));
    }
}
