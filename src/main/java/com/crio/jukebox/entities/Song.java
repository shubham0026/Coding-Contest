package com.crio.jukebox.entities;

import java.util.List;

public class Song extends BaseEntity{
    private String songName;
    private Artist artist;
    private String genre;
    private Album album;
    //Ask the interviewer that while fetching a song do we need info of album as well
    //if yes then we can go for bidirectional relationship and can have Album object
    //as a member here if no then ignore.
    List<Artist> featuredArtists;

    public Song(String id,String songName, String genre, Album album, Artist songArtist, List<Artist> featuredArtists) {
        this.songName = songName;
        this.artist = songArtist;
        this.genre = genre;
        this.album = album;
        this.featuredArtists = featuredArtists;
        this.id = id;
    }
    public void playSong(){
        System.out.println("Playing the song "+songName);
    }

    public String getSongName() {
        return songName;
    }

    public Artist getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public List<Artist> getFeaturedArtists() {
        return featuredArtists;
    }
    
    public Album getAlbum() {
        return album;
    }
    @Override
    public String toString() {
        return "Song [artist=" + artist + ", featuredArtists=" + featuredArtists + ", genre="
                + genre + ", album=" + album + ", songName=" + songName + "]";
    }

    
}
