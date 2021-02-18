package com.commands;

import com.app.Command;
import com.interaction.InteractionInterface;
import com.exceptions.MoreArgumentsRequiredException;
import com.interaction.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

public class Exit extends Command {

    public Exit() {
        cmdLine = "exit";
        description = "выход из программы";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException, MoreArgumentsRequiredException {
        String confirmation = ui.readNecessaryArgument("Запрошен выход из приложения без сохранения, вы хотите продолжить?");
        if(confirmation.equals("yes")) {
            System.out.println("До новых встреч");
            System.exit(0);
        }
    }
}
