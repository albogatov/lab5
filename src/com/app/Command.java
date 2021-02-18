package com.app;

import com.exceptions.MoreArgumentsRequiredException;
import com.exceptions.NonExistingCommandException;
import com.interaction.InteractionInterface;
import com.interaction.UserInterface;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Command {
    protected String cmdLine;
    protected String description;
    public abstract void execute(UserInterface ui, String[] arguments, InteractionInterface interactiveStorage) throws IOException, MoreArgumentsRequiredException, NonExistingCommandException;

    public Command() {

    }
    /**
    public Command(String line, String description) {
        this.cmdLine = line;
        this.description = description;
    } */
    public String getCommand() {
        return cmdLine;
    }
    public String getHelp() {
        return description;
    }
}
