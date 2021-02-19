package commands;

import app.Command;
import elements.Worker;
import interaction.InteractionInterface;
import exceptions.MoreArgumentsRequiredException;
import interaction.UserInterface;

import java.io.IOException;

public class AddIfMin extends Command {

    public AddIfMin() {
        cmdLine = "add_if_min";
        description = "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции: " +
                "ввести имя, оклад сотрудника";
    }

    public void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException, MoreArgumentsRequiredException {
        if(arguments.length < 3)
            throw new MoreArgumentsRequiredException("Введено слишком мало аргументов");
        int size1 = interactiveStorage.getSize();
        Worker worker = ui.readWorker(arguments, 1);
        interactiveStorage.add_if_min(worker);
        int size2 = interactiveStorage.getSize();
        if(size2 > size1)
            ui.displayMessage("Элемент успешно добавлен");
        else ui.displayMessage("Элемент не добавлен, т.к. он не подходит критерию");
    }
}
