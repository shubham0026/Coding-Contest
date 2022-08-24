package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.services.IPlaylistService;

public class AddSongInPlaylistCommand implements ICommand{
    private final IPlaylistService playlistService;
    
    public AddSongInPlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }
    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String userId = tokens.get(2);
        String playListId = tokens.get(3);
        List<String> songIds = new ArrayList<String>();
        for(int i=4;i<tokens.size();i++){
            songIds.add(tokens.get(i));
        }
        Playlist playlist = playlistService.addSongInPlaylistCommand(userId,playListId,songIds);
        System.out.println("Playlist ID - "+playlist.getId());
        System.out.println("Playlist Name - "+playlist.getPlaylistName());
        List<String> finalSongIdsList = playlist.getSongsInPlaylist();
        System.out.print("Song IDs - ");
        for(String finalSongIds : finalSongIdsList){
            System.out.print(finalSongIds+" ");
        }
        System.out.println();
    }
    
}
