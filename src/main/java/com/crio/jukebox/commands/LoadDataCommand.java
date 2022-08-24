package com.crio.jukebox.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.crio.jukebox.services.ISongService;

public class LoadDataCommand implements ICommand{

    private final ISongService songService;
    
    public LoadDataCommand(ISongService songService) {
        this.songService = songService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String fileName = tokens.get(1);
        String line = ""; 
		String splitBy = ",";
		try   
		{  
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader("/home/crio-user/workspace/shubhams0026-ME_OBJECT_MODELING_V2/"+fileName));  
			while ((line = br.readLine()) != null && !line.equals("======="))   //returns a Boolean value  
			{  
				if(line.equals("<<<<<<< HEAD")) continue;
				
				String[] csvTokens = line.split(splitBy);    // use comma as separator 
				songService.createSongPool(csvTokens);
				
			}  
			System.out.println("Songs Loaded successfully");
		}   
		catch (IOException e)   
		{  
			e.printStackTrace();  
		}  
    }
    
}
