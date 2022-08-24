package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;

public interface IPlaylistService {

    public Playlist createPlaylist(String userId, String playListName, List<String> songIds);
    public void deletePlaylist(String userId,String playlistId);
    public Playlist addSongInPlaylistCommand(String userId, String playListId,
            List<String> songIds);
    public Playlist deleteSongInPlaylistCommand(String userId, String playListId,
            List<String> songIds);
    public Song playPlaylist(String userId, String playlistId);
    public Song playSong(String userId, String param);
}
