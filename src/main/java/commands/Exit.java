package commands;

import app.Command;
import interaction.InteractionInterface;
import exceptions.MoreArgumentsRequiredException;
import interaction.UserInterface;

import java.io.IOException;

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
