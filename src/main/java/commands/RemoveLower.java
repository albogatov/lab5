package commands;

import app.Command;
import elements.Worker;
import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.IOException;

public class RemoveLower extends Command {

    public RemoveLower() {
        cmdLine = "removeLower";
        description = "удалить из коллекции все элементы, меньшие, чем заданный";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException {
        int size1 = interactiveStorage.getSize();
        Worker worker = ui.readWorker(arguments, 1);
        interactiveStorage.remove_lower(worker);
        int size2 = interactiveStorage.getSize();
        if (size2 < size1)
            ui.displayMessage("Операция успешно выполнена");
    }
}
