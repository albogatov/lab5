package com.commands;

import com.app.Command;
import com.interaction.InteractionInterface;
import com.exceptions.MoreArgumentsRequiredException;
import com.interaction.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

public class PrintAscending extends Command {

    public PrintAscending() {
        cmdLine = "print_ascending";
        description = "вывести элементы коллекции в порядке возрастания";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException, MoreArgumentsRequiredException {
        interactiveStorage.print_ascending();
    }
}
