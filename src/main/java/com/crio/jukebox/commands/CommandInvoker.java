package com.crio.jukebox.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crio.codingame.exceptions.NoSuchCommandException;

public class CommandInvoker {
    // This map stores command name and an Icommand object which contains info about a command
    private static final Map<String, ICommand> commandMap = new HashMap<>();

    // Register the command into the HashMap
    public void register(String commandName, ICommand command){
        commandMap.put(commandName,command);
    }

    // Get the registered Command
    private ICommand get(String commandName){
        return commandMap.get(commandName);
    }

    // Execute the registered Command
    public void executeCommand(String commandName, List<String> tokens) throws NoSuchCommandException {
        String str="";
        if(tokens.get(1).equals("ADD-SONG"))
            str+=" ADD-SONG";
        
        else if(tokens.get(1).equals("DELETE-SONG"))
            str+=" DELETE-SONG";
        ICommand command = get(commandName+str); // Get the registered Command object
        if(command == null){
            // Handle Exception
            throw new NoSuchCommandException();
        }
        
        command.execute(tokens);
    }

}
