package commands;

import app.Command;
import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import com.opencsv.CSVWriter;

public class Save extends Command {

    public Save() {
        cmdLine = "save";
        description = "сохранить коллекцию в файл";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException {
        String filePath = arguments[1];
        interactiveStorage.save(filePath);
        ui.displayMessage("Коллекция сохранена в файл");
    }
}
