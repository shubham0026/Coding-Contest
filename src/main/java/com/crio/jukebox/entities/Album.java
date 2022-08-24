package com.crio.jukebox.entities;

import java.util.List;

public class Album {
    String albumName;
    //Note here we are not storing Album_Artist because that we can derive from list of songs.
    //As every song object has artist
    //List<Song> listOfSongs;
    public Album(String albumName) {
        this.albumName = albumName;
        //this.listOfSongs = listOfSongs;
    }
    public String getAlbumName() {
        return albumName;
    }
    // public List<Song> getListOfSongs() {
    //     return listOfSongs;
    // }

    
}
