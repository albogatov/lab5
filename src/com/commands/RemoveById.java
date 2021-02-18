package com.commands;

import com.app.Command;
import com.elements.Worker;
import com.interaction.InteractionInterface;
import com.exceptions.MoreArgumentsRequiredException;
import com.interaction.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

public class RemoveById extends Command {

    public RemoveById() {
        cmdLine = "remove_by_id";
        description = "удалить элемент из коллекции по его id";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException, MoreArgumentsRequiredException {
        if(arguments.length < 2)
            throw new MoreArgumentsRequiredException("Введено слишком мало аргументов");
        long id = Long.parseLong(arguments[1]);
        Worker worker;
        if(interactiveStorage.findById(id)) {
            interactiveStorage.remove_by_id(id);
            ui.displayMessage("Сотрудник удален");
        }
    }
}
