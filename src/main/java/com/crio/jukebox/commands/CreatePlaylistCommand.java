package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.services.IPlaylistService;

public class CreatePlaylistCommand implements ICommand{
    private final IPlaylistService playlistService;
    
    public CreatePlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String userId = tokens.get(1);
        String playListName = tokens.get(2);
        List<String> songIds = new ArrayList<String>();
        for(int i=3;i<tokens.size();i++){
            songIds.add(tokens.get(i));
        }
        Playlist playlist = playlistService.createPlaylist(userId,playListName,songIds);
        System.out.println("Playlist ID - "+playlist.getId());
    }
    
}
