package commands;

import app.Command;
import app.CommandCenter;
import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.*;
import java.util.HashSet;
import java.util.NoSuchElementException;

/**
 * Класс команды executeScript
 */
public class ExecuteScript extends Command {
    private static final HashSet<String> paths = new HashSet<>();
    private static boolean success;

    /**
     * Стандартный конструктор, добавляющий строку вызова и описание команды
     */
    public ExecuteScript() {
        cmdLine = "executeScript";
        description = "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";
    }

    /**
     * Метод исполнения
     *
     * @param ui                 объект, через который ведется взаимодействие с пользователем
     * @param arguments          необходимые для исполнения аргументы
     * @param interactiveStorage объект для взаимодействия с коллекцией
     * @throws IOException в случае ошибки ввода/вывода
     */
    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException {
        try {
            UserInterface scriptInteraction = new UserInterface(new FileReader(arguments[1]), new OutputStreamWriter(System.out), false);
            String line;
            String path = arguments[1];
            success = true;
            while (scriptInteraction.hasNextLine()) {
                line = scriptInteraction.read();
                String cmd = line.split(" ")[0];
                if (cmd.equals("executeScript")) {
                    if (!paths.contains(path)) {
                        paths.add(path);
                        CommandCenter.getInstance().executeCommand(scriptInteraction, cmd, line, interactiveStorage);
                    } else {
                        paths.clear();
                        throw new StackOverflowError("Выполнение скрипта остановлено, т.к. возможна рекурсия");
                    }
                } else CommandCenter.getInstance().executeCommand(scriptInteraction, cmd, line, interactiveStorage);
            }
            paths.clear();
            if (success)
                ui.displayMessage("Скрипт выполнен");
            else ui.displayMessage("Скрипт не выполнен");
        } catch (FileNotFoundException e) {
            ui.displayMessage("В качестве аргумента указан путь к несуществуюшему файлу");
            success = false;
            paths.clear();
        } catch (NoSuchElementException e) {
            ui.displayMessage("Скрипт некорректен, проверьте верность введенных команд");
            success = false;
            paths.clear();
        } catch (Exception e) {
            ui.displayMessage("Произошла неизвестная ошибка");
            success = false;
            paths.clear();
        }
    }
}

