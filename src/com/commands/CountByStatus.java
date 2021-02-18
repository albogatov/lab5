package com.commands;

import com.app.Command;
import com.elements.Status;
import com.interaction.InteractionInterface;
import com.exceptions.MoreArgumentsRequiredException;
import com.interaction.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

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
