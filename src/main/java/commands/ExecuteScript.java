package commands;

import app.Command;
import app.CommandCenter;
import exceptions.NonExistingCommandException;
import interaction.InteractionInterface;
import exceptions.MoreArgumentsRequiredException;
import interaction.UserInterface;

import java.io.*;

public class ExecuteScript extends Command {

    public ExecuteScript() {
        cmdLine = "execute_script";
        description = "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException, MoreArgumentsRequiredException, NonExistingCommandException {
        if(arguments.length < 2)
            throw new MoreArgumentsRequiredException("Введено слишком мало аргументов");
        File file = new File(arguments[1]); // рекурсия рассмотреть
        UserInterface scriptInteraction = new UserInterface(new FileReader(file), new OutputStreamWriter(System.out), false);
        String line;
        do {
            line = scriptInteraction.read();
            String cmd = line.split(" ")[0];
            CommandCenter.getInstance().executeCommand(scriptInteraction, cmd, line, interactiveStorage);
        } while(scriptInteraction.hasNextLine());
        ui.displayMessage("Скрипт выполнен");
    }
}
