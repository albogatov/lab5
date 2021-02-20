package commands;

import app.Command;
import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Save extends Command {

    public Save() {
        cmdLine = "save";
        description = "сохранить коллекцию в файл";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException {
        String filePath = arguments[1];
        PrintWriter pw = new PrintWriter(new FileOutputStream(filePath));
    }
}
