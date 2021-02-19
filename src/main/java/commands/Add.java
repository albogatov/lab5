package commands;

import app.Command;
import exceptions.MoreArgumentsRequiredException;
import interaction.InteractionInterface;
import interaction.UserInterface;
import elements.Worker;

import java.io.IOException;

public class Add extends Command {
    public Add() {
        cmdLine = "add";
        description = "Добавить новый элемент в коллекцию. При вызове команды необходимо " +
                "ввести имя, оклад сотрудника";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException, MoreArgumentsRequiredException {
        if (arguments.length < 3) {
            throw new MoreArgumentsRequiredException("Введено слишком мало аргументов");
        }
        Worker worker = ui.readWorker(arguments, 1);
        interactiveStorage.add(worker);
        ui.displayMessage("Сотрудник успешно добавлен");
    }
}
