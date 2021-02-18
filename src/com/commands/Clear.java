package com.commands;

import com.app.Command;
import com.interaction.InteractionInterface;
import com.exceptions.MoreArgumentsRequiredException;
import com.interaction.StorageInteraction;
import com.interaction.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

public class Clear extends Command {

    public Clear() {
        cmdLine = "clear";
        description = "очистить коллекцию";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException, MoreArgumentsRequiredException {
        interactiveStorage.clear();
        if(interactiveStorage.getSize() > 0)
            System.out.println("Что-то пошло не так, попробуйте еще раз");
        else System.out.println("Коллекция очищена");
    }
}
