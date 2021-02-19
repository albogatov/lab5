package commands;

import app.Command;
import elements.Worker;
import interaction.InteractionInterface;
import exceptions.MoreArgumentsRequiredException;
import interaction.UserInterface;

import java.io.IOException;

public class RemoveGreater extends Command {

    public RemoveGreater() {
        cmdLine = "remove_greater";
        description = "удалить из коллекции все элементы, превышающие заданный";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException, MoreArgumentsRequiredException {
        if (arguments.length < 3)
            throw new MoreArgumentsRequiredException("Введено слишком мало аргументов");
        int size1 = interactiveStorage.getSize();
        Worker worker = ui.readWorker(arguments, 1);
        interactiveStorage.remove_greater(worker);
        int size2 = interactiveStorage.getSize();
        if (size2 < size1)
            ui.displayMessage("Операция успешно выполнена");
    }
}