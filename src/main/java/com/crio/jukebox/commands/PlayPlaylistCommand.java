package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.services.IPlaylistService;

public class PlayPlaylistCommand implements ICommand{

    private final IPlaylistService playlistService;
    
    public PlayPlaylistCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }
    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String userId = tokens.get(1);
        String playlistId = tokens.get(2);
        Song currentSong = playlistService.playPlaylist(userId,playlistId);
        System.out.println("Current Song Playing");
        System.out.println("Song - "+currentSong.getSongName());
        System.out.println("Album - "+currentSong.getAlbum().getAlbumName());
        List<Artist> featuredArtists = currentSong.getFeaturedArtists();
        System.out.print("Artists - ");
        for(Artist artist : featuredArtists){
            System.out.print(artist+",");
        }
        System.out.println();
    }
    
}
