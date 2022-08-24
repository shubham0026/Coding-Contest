package com.crio.jukebox.entities;

import java.util.List;

public class User extends BaseEntity{
    public static Integer autoIncrement=0;
    private final String userName;
    private UserPlaylists userPlaylists;

    public User(String name) {
        this.id = Integer.toString(++autoIncrement);
        this.userName = name;
    }
    public void setUserPlaylists(UserPlaylists userPlaylists) {
        this.userPlaylists = userPlaylists;
    }
    public String getName() {
        return userName;
    }
    public UserPlaylists getUserPlaylists() {
        return userPlaylists;
    }
    public List<Playlist> getPlaylistByUsers(User user) {
        if(userPlaylists==null)
            return null;
        return userPlaylists.getPlaylistByUsers(user);
    }
    public void addUserPlaylist(User user, List<Playlist> pList) {
        userPlaylists.addUserPlaylist(user, pList);
    }
    @Override
    public String toString() {
        return "User [name=" + userName + ", userPlaylists=" + userPlaylists + "]";
    }
    
}
