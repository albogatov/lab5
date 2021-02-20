package commands;

import app.Command;
import elements.Worker;
import interaction.InteractionInterface;

import interaction.UserInterface;

import java.io.IOException;

public class RemoveById extends Command {

    public RemoveById() {
        cmdLine = "removeById";
        description = "удалить элемент из коллекции по его id";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException {
        long id = Long.parseLong(arguments[1]);
        Worker worker;
        if (interactiveStorage.findById(id)) {
            interactiveStorage.remove_by_id(id);
            ui.displayMessage("Сотрудник удален");
        }
    }
}
