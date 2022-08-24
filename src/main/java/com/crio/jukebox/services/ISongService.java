package com.crio.jukebox.services;

import com.crio.jukebox.entities.Song;

public interface ISongService {
    public Song createSongPool(String[] csvTokens);
}
