package com.crio.jukebox.entities;

import java.util.List;

public class Playlist extends BaseEntity {
    private final String playlistName;
    private List<String> songsInPlaylist;
    private String currentSongId;

    public Playlist(String id,String playlistName,List<String> songsInPlaylist) {
        this.id = id;
        this.playlistName = playlistName;
        this.songsInPlaylist = songsInPlaylist;
    }
    public void removeSongsInPlayList(String songId){
        songsInPlaylist.remove(songId);
    }
    public void addSongsInPlaylist(String songId){
        songsInPlaylist.add(songId);
    }
    public List<String> getSongsInPlaylist() {
        return songsInPlaylist;
    }
    public String getPlaylistName() {
        return playlistName;
    }
    public String getCurrentSongId() {
        if(currentSongId==null)  currentSongId = songsInPlaylist.get(0);
        return currentSongId;
    }
    public void setCurrentSongId(String currentSongId) {
        this.currentSongId = currentSongId;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Playlist other = (Playlist) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Playlist [id=" + id + ",playlistName=" + playlistName + ", songsInPlaylist=" + songsInPlaylist
                + "]";
    }
}
