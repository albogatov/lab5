package commands;

import app.Command;
import app.CommandCenter;
import exceptions.NonExistingCommandException;
import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.*;
import java.util.HashSet;

public class ExecuteScript extends Command {
    private static final HashSet<String> paths = new HashSet<>();

    public ExecuteScript() {
        cmdLine = "executeScript";
        description = "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException, NonExistingCommandException {
        try {
            UserInterface scriptInteraction = new UserInterface(new FileReader(new File(arguments[1])), new OutputStreamWriter(System.out), false);
            String line;
            String path = arguments[1];
            boolean success = true;
            while (scriptInteraction.hasNextLine()) {
                line = scriptInteraction.read();
                String cmd = line.split(" ")[0];
                if(cmd.equals("executeScript")) {
                    if(!paths.contains(path)) {
                        paths.add(path);
                        CommandCenter.getInstance().executeCommand(scriptInteraction, cmd, line, interactiveStorage);
                    }
                    else {
                        paths.clear();
                        throw new StackOverflowError("Выполнение скрипта приостановлено, т.к. возможна рекурсия");
                    }
                }
                else CommandCenter.getInstance().executeCommand(scriptInteraction, cmd, line, interactiveStorage);
            }
            paths.clear();
            if(success)
                ui.displayMessage("Скрипт выполнен");
            else ui.displayMessage("Скрипт не выполнен");
        } catch (FileNotFoundException e) {
            ui.displayMessage("В качестве аргумента указан путь к несуществуюшему файлу");
            paths.clear();
        } catch (Exception e) {
            ui.displayMessage("Произошла неизвестаня ошибка");
            paths.clear();
        }
    }
}

