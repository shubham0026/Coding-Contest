package com.crio.jukebox.entities;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// This is a seperate relationship model which denotes relationship 
// between User and Playlist and stores information about playlist specific to a user
// for eg: list of all playlist specific to a user.
public class UserPlaylists {
    // A good practice is to Write immutable classes,variables,collections when you can.
    // A user can have multiple playlists saved.
    private final Map<User,List<Playlist>> userPlaylistMap;
    private Playlist activePlayList;
    public UserPlaylists(){
        userPlaylistMap = new HashMap<User,List<Playlist>>();
    }

    public UserPlaylists(Map<User, List<Playlist>> userPlaylistMap) {
        this.userPlaylistMap = userPlaylistMap;
    }

    public Playlist getActivePlayList() {
        return activePlayList;
    }

    public void setActivePlayList(Playlist activePlayList) {
        this.activePlayList = activePlayList;
    }

    public void addUserPlaylist(User user, List<Playlist> pList){
        userPlaylistMap.put(user, pList);
    }

    public List<Playlist> getPlaylistByUsers(User user){
        return userPlaylistMap.get(user);
    }

    public boolean checkIfPlaylistExist(User user,String playlistId){
        List<Playlist> existingPlaylists = getPlaylistByUsers(user);
        for(Playlist playlist:existingPlaylists){
            String id = playlist.getId();
            if(id.equals(playlistId)){
                return true;
            }
        }
        return false;
    }
    public Playlist findPlayListById(User user,String playlistId){
        List<Playlist> existingPlaylists = user.getPlaylistByUsers(user);
        for(Playlist playlist:existingPlaylists){
            String id = playlist.getId();
            if(id.equals(playlistId)){
                return playlist;
            }
        }
        return null;
    }
    public void deletePlaylist(User user,String playlistId){
        List<Playlist> existingPlaylists = getPlaylistByUsers(user);
        for(Playlist playlist:existingPlaylists){
            String id = playlist.getId();
            if(id.equals(playlistId)){
                existingPlaylists.remove(playlist);
                break;
            }
        }  
    }
    public Map<User, List<Playlist>> getUserPlaylistMap() {
        return userPlaylistMap;
    }
   
    @Override
    public String toString() {
        return "UserPlaylist [userPlaylistMap=" + userPlaylistMap + "]";
    }
    
}
