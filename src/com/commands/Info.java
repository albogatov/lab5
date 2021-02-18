package com.commands;

import com.app.Command;
import com.interaction.InteractionInterface;
import com.exceptions.MoreArgumentsRequiredException;
import com.interaction.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

public class Info extends Command {

    public Info() {
        cmdLine = "info";
        description = "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException, MoreArgumentsRequiredException {
        interactiveStorage.info();
    }
}
