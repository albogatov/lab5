package com.commands;

import com.app.Command;
import com.elements.Worker;
import com.interaction.InteractionInterface;
import com.exceptions.MoreArgumentsRequiredException;
import com.interaction.UserInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Update extends Command {

    public Update() {
        cmdLine = "update";
        description = "обновить значение элемента коллекции, id которого равен заданному";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException, MoreArgumentsRequiredException {
        if (arguments.length < 2)
            throw new MoreArgumentsRequiredException("Введено слишком мало аргументов");
        long id = Long.parseLong(arguments[1]);
        Worker worker;
        if (interactiveStorage.findById(id)) {
            arguments = ui.reReadArguments("Введите имя и оклад сотрудника: ");
            if (arguments.length < 2)
                throw new MoreArgumentsRequiredException("Введено слишком мало аргументов");
            if (arguments[0].isEmpty() || arguments[1].isEmpty()) {
                do {
                    arguments = ui.reReadArguments("Введите имя и оклад сотрудника: ");
                } while (arguments[0].isEmpty() || arguments[1].isEmpty());
            }
            worker = ui.readWorker(arguments, 0);
            interactiveStorage.update(id, worker);
            ui.displayMessage("Сотрудник добавлен");
        }
    }
}
