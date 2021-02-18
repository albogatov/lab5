package com.commands;

import com.app.*;
import com.elements.Worker;
import com.exceptions.MoreArgumentsRequiredException;
import com.interaction.InteractionInterface;
import com.interaction.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

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
