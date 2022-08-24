package com.crio.jukebox.entities;

public class Artist {
    String artistName;

    public Artist(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artistName='" + artistName + '\'' +
                '}';
    }
}
