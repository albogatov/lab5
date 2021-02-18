package com.commands;

import com.app.Command;
import com.interaction.InteractionInterface;
import com.exceptions.MoreArgumentsRequiredException;
import com.interaction.UserInterface;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Save extends Command {

    public Save() {
        cmdLine = "save";
        description = "сохранить коллекцию в файл";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException, MoreArgumentsRequiredException {
        if(arguments.length < 2)
            throw new MoreArgumentsRequiredException("Введено слишком мало аргументов");
        String filePath = arguments[1];
        PrintWriter pw = new PrintWriter(new FileOutputStream(filePath));
    }
}
