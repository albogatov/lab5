package app;

import exceptions.NonExistingCommandException;
import interaction.InteractionInterface;
import interaction.UserInterface;

import java.io.IOException;

public abstract class Command {
    protected String cmdLine;
    protected String description;

    public abstract void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException, NonExistingCommandException;

    public Command() {

    }

    /**
     * public Command(String line, String description) {
     * this.cmdLine = line;
     * this.description = description;
     * }
     */
    public String getCommand() {
        return cmdLine;
    }

    public String getHelp() {
        return description;
    }
}
