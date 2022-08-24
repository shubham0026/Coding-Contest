package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Artist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.ISongRepository;

public class SongService implements ISongService {
    private final ISongRepository songRepository;

    public SongService(ISongRepository songRepository) {
        this.songRepository = songRepository;
    }
    public Song createSongPool(String[] csvTokens){
        String id = csvTokens[0];
        String songName = csvTokens[1];
        String genre = csvTokens[2];
        String albumName = csvTokens[3];
        Album album = new Album(albumName);
        String artistName =  csvTokens[4];
        Artist songArtist = new Artist(artistName);
        List<Artist> featuredArtists = new ArrayList<Artist>();
        List<String> list = new ArrayList<String>(Arrays.asList(csvTokens[5].split("#")));
        for(String str:list){
            Artist artistFeat = new Artist(str);
            featuredArtists.add(artistFeat);
        }
        Song song = songRepository.save(new Song(id,songName,genre,album,songArtist,featuredArtists));
        return song;
    }
}
