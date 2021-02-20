package commands;

import app.Command;
import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.IOException;

public class Clear extends Command {

    public Clear() {
        cmdLine = "clear";
        description = "очистить коллекцию";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) {
        interactiveStorage.clear();
        if (interactiveStorage.getSize() > 0)
            System.out.println("Что-то пошло не так, попробуйте еще раз");
        else System.out.println("Коллекция очищена");
    }
}
