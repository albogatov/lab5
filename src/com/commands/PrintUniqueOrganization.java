package com.commands;

import com.app.Command;
import com.interaction.InteractionInterface;
import com.exceptions.MoreArgumentsRequiredException;
import com.interaction.UserInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PrintUniqueOrganization extends Command {

    public PrintUniqueOrganization() {
        cmdLine = "print_unique_organization";
        description = "вывести уникальные значения поля organization всех элементов в коллекции";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException, MoreArgumentsRequiredException {
        List<String> result = interactiveStorage.print_unique_organization();
        Iterator itr = result.iterator();
        while(itr.hasNext()) {
            ui.displayMessage(itr.next().toString());
        }
    }
}
